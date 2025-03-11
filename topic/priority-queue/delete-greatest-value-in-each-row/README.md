# Delete Greatest Value in Each Row

## 🚀 Problem  
You are given an `m x n` matrix `grid` consisting of positive integers.
Perform the following operation until `grid` becomes empty:
- Delete the element with the greatest value from each row. If multiple such elements exist, delete any of them.
- Add the maximum of deleted elements to the answer.
  
Note that the number of columns decreases by one after each operation.
Return the answer after performing the operations described abocve.

## 📝 Examples  

### Example 1
```
Input: grid = [[1,2,4],[3,3,1]]
Output: 8
Explanation: 
```

## ⚡ Constraints  
- `m == grid.length`
- `n == grid[i].length`
- `1 <= m, n <= 50`
- `1 <= grid[i][j] <= 100`

## 📚 Related Topics  
- Priority Queue
- Heap

## 🔗 References  
- https://leetcode.com/problems/delete-greatest-value-in-each-row/
