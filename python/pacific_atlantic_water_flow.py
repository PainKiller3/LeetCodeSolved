# Problem Link: https://leetcode.com/problems/pacific-atlantic-water-flow/description/

class Solution:
    def pacificAtlantic(self, heights):
        if not heights or not heights[0]:
            return []

        rows, cols = len(heights), len(heights[0])

        # Mark reachable for both oceans
        pacific_reachable = [[False] * cols for _ in range(rows)]
        atlantic_reachable = [[False] * cols for _ in range(rows)]

        # Directions for up, down, left, right movements
        directions = [(-1, 0), (1, 0), (0, -1), (0, 1)]

        def dfs(row, col, ocean_reachable):
            ocean_reachable[row][col] = True
            for dr, dc in directions:
                new_row, new_col = row + dr, col + dc
                if 0 <= new_row < rows and 0 <= new_col < cols and not ocean_reachable[new_row][new_col] and heights[new_row][new_col] >= heights[row][col]:
                    dfs(new_row, new_col, ocean_reachable)

        # Perform DFS from Pacific Ocean borders (top and left edges)
        for i in range(rows):
            dfs(i, 0, pacific_reachable)  # Left border
            dfs(i, cols - 1, atlantic_reachable)  # Right border

        for j in range(cols):
            dfs(0, j, pacific_reachable)  # Top border
            dfs(rows - 1, j, atlantic_reachable)  # Bottom border

        # Find cells that can reach both oceans
        result = []
        for i in range(rows):
            for j in range(cols):
                if pacific_reachable[i][j] and atlantic_reachable[i][j]:
                    result.append([i, j])

        return result
