from typing import List

class Solution:
    def findContentChildren(self, g: List[int], s: List[int]) -> int:
        g.sort()
        s.sort()

        child_i = cookie_i = result = 0

        while child_i < len(g) and cookie_i < len(s):
            if s[cookie_i] >= g[child_i]:
                result += 1
                child_i += 1
            cookie_i += 1

        return result
