#
# @lc app=leetcode.cn id=146 lang=python3
#
# [146] LRU 缓存
# 使用双向链表，然后注意首尾都设置一个实的节点

# @lc code=start
class DLinkedNode:
    def __init__(self,key=0,value=0,prev=None,next=None):
        self.key=key
        self.value=value
        self.prev=prev
        self.next=next
class LRUCache:
    def __init__(self, capacity: int):
        self.capacity=capacity
        self.count=0
        self.head=DLinkedNode(-1,-1)
        self.tail=DLinkedNode(-1,-1,self.head,None)
        self.head.next=self.tail

    def get(self, key: int) -> int:
        cur=self.head
        while cur!=self.tail:
            if cur.key==key:
                value=cur.value
                self.remove(cur)
                self.put(key,value)
                return value
            cur=cur.next
        return -1

    def put(self, key: int, value: int) -> None:
        cur=self.head
        while cur:
            if cur.key==key:
                self.remove(cur)
                self.put(key,value)
                return
            cur=cur.next
        if self.count==self.capacity:
            self.removeTail()
        self.putHead(key,value)
    
    def remove(self, node: DLinkedNode) -> None:
        pre=node.prev
        next=node.next
        pre.next=next
        next.prev=pre
        self.count-=1
    
    def removeTail(self) -> None:
        node=self.tail.prev
        self.remove(node)
        
    def putHead(self,key:int,value: int) -> None:
        next=self.head.next
        node=DLinkedNode(key,value,self.head,next)
        self.head.next=node
        next.prev=node
        self.count+=1

# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)
# @lc code=end

