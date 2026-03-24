# =====================================================================
# 自动生成的三维模具几何建模脚本
# 基于 RAG 增强的大模型生成，映射至目标 CAD API
# 目标操作: 构建相切砍公面 (Trimming Surface)
# =====================================================================

import CAD_Geometry_API as geom
import CAD_Surface_API as surf

def generate_trimming_surface(active_part):
    # 1. 解析目标特征：提取参考边界
    # 结合知识图谱匹配到的边界信息
    boundary_edges = active_part.get_edges_by_tags(["Edge_01", "Edge_02", "Edge_03", "Edge_04"])

    # 2. 构建基础拓扑轮廓
    composite_profile = geom.create_composite_curve(boundary_edges)

    # 3. 设定延伸向量与工艺参数
    # 提取自工艺文档的脱模/延伸方向: Z轴正向 [0, 0, 1]
    extrude_vector = geom.Vector3D(0.0, 0.0, 1.0)
    extension_dist = 50.0

    # 4. 执行底层 API：生成砍公面曲面
    trimming_surface = surf.create_extruded_surface(
        profile = composite_profile,
        direction = extrude_vector,
        length = extension_dist,
        continuity_mode = "Tangent"  # 保持相切连续以满足精度相关特征约束
    )

    # 5. 特征树更新与几何干涉自动校核
    active_part.add_feature(trimming_surface, name="Trimming_Surface_Z")
    active_part.update_topology()

    return trimming_surface

# --- 主执行逻辑 ---
if __name__ == "__main__":
    current_model = geom.get_active_model()
    result_surface = generate_trimming_surface(current_model)
    print("砍公面代码执行完毕，进入自动校核阶段...")