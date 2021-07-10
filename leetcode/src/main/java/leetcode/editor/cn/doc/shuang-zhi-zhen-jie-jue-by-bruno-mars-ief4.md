### 解题思路
哈希函数更好，给大家提供另一种思路：双指针
### 代码

```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
      //使用双指针
      final int n = nums.length;
      int[][] p = new int[n][2];
      for(int i = 0;i < n; ++i) {
          p[i][0] = nums[i];
          p[i][1] = i;
      }
      Arrays.sort(p,(a,b) -> a[0] - b[0]);
      for(int i = 0,j = n-1;i < j;) {
          final int x  = p[i][0] + p[j][0];
          if(x == target) {
              return new int[] {p[i][1],p[j][1]};
          }
          if(x < target) {
              ++i;
          }else {
              --j;
          }
      }
      return null;
    }
}
```