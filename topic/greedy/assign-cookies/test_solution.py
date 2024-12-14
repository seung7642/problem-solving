import unittest
from solution import Solution

class TestSolution(unittest.TestCase):
    def setUp(self):
        self.solution = Solution()

    def test_not_enough_cookies(self):
        self.assertEqual(self.solution.findContentChildren([1,2,3], [1,1]), 1)

    def test_all_children_get_cookies(self):
        self.assertEqual(self.solution.findContentChildren([1,2], [1,2,3]), 2)

    def test_one_big_cookie(self):
        self.assertEqual(self.solution.findContentChildren([1,2,3], [3]), 1)

    def test_no_children(self):
        self.assertEqual(self.solution.findContentChildren([], [1,2,3]), 0)

    def test_no_cookies(self):
        self.assertEqual(self.solution.findContentChildren([1,2,3], []), 0)

if __name__ == '__main__':
    unittest.main()
