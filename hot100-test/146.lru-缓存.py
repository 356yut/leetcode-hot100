#
# @lc app=leetcode.cn id=146 lang=python3
#
# [146] LRU 缓存
#

# @lc code=start
class DlinkedNode:
    def __init__(self,key=0,value=0,prev=None,next=None):
        self.key=key
        self.value=value
        self.prev=prev
        self.next=next
        
class LRUCache:

    def __init__(self, capacity: int):
        self.head=None
        self.tail=None
        self.count=0
        self.capacity=capacity
        

    def get(self, key: int) -> int:
        cur=self.head
        while(cur):
            if(cur.key==key):
                value=cur.value
                if cur!=self.head:
                    self.remove(cur)
                    self.put(key,value)
                return value
            cur=cur.next
        return -1

    def put(self, key: int, value: int) -> None:
        if self.get(key)!=-1:
            cur=self.head
            while(cur):
                if(cur.key==key):
                    cur.key
        if self.count==0:
            self.head=DlinkedNode(key,value,None,None)
            self.tail=self.head
        else:
            newHead=DlinkedNode(key,value,None,self.head)
            self.head.prev=newHead
            self.head=newHead
        self.count+=1
        if self.count==self.capacity+1:
            newTail=self.tail.prev
            newTail.next=None
            self.tail=newTail
            self.count-=1
        
    def remove(self,node: DlinkedNode)-> None:
        if node==self.tail:
            newTail=self.tail.prev
            newTail.next=None
            self.tail=newTail
            self.count-=1
        else:
            pre=node.prev
            pre.next=node.next
            pre.next.prev=pre
            self.count-=1
            


# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)
# @lc code=end

