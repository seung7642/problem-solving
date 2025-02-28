from queue import PriorityQueue


class Solution:
    def findRelativeRanks(self, score: List[int]) -> List[str]:
        pq = PriorityQueue()
        for s in score:
            pq.put((-s))

        dic = {}
        idx = 1
        while not pq.empty():
            if idx == 1:
                dic[-pq.get()] = "Gold Medal"
            elif idx == 2:
                dic[-pq.get()] = "Silver Medal"
            elif idx == 3:
                dic[-pq.get()] = "Bronze Medal"
            else:
                dic[-pq.get()] = idx

            idx += 1

        return [str(dic[s]) for s in score]
