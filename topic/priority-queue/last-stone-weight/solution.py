import heapq

class Solution:
    def lastStoneWeight(self, stones: List[int]) -> int:
        if len(stones) == 1:
            return stones[0]

        stones = [-val for val in stones]
        heapq.heapify(stones)
        while len(stones) >= 2:
            y = -heapq.heappop(stones)
            x = -heapq.heappop(stones)
            if x != y:
                y -= x
                heapq.heappush(stones, -y)

        return 0 if not stones else -stones[0]
