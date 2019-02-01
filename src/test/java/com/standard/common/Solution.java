package com.standard.common;

import org.junit.Test;

/**
 * @author Jiangkui
 * @since 2018/12/28 16:12
 */
public class Solution {
    /**
     * Given nums = [2, 7, 11, 15], target = 9,
     * <p>
     * Because nums[0] + nums[1] = 2 + 7 = 9,
     * return [0, 1].
     */

    @Test
    public void twoSum() {
        int[] nums = {3, 2, 4};
        int[] result = twoSum(nums, 6);
        System.out.println("");
    }


    private int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }


    /**
     * Given a 32-bit signed integer, reverse digits of an integer.
     * <p>
     * Example 1:
     * <p>
     * Input: 123
     * Output: 321
     * Example 2:
     * <p>
     * Input: -123
     * Output: -321
     * Example 3:
     * <p>
     * Input: 120
     * Output: 21
     */

    @Test
    public void reverse() {
        int reverse = reverse(123);
        int reverse1 = reverse1(1534236469);
        System.out.println(reverse);
    }

    private int reverse(int x) {
        int ans = 0;
        while (x != 0) {
            int temp = ans * 10 + x % 10;
            if (temp / 10 != ans) return 0; // 防止溢出32位整数上下界
            ans = temp;
            x /= 10;
        }
        return ans;
    }

    private int reverse1(int x) {
        int max_value = 0x80000000;//2147483647
        int min_vale = 0x7fffffff;//-2147483648
        int result = 0;
        while (x != 0) {
            result = result * 10 + x % 10;
            x /= 10;
        }
        return result;
    }

    // standard answer
    private int reverseA(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            if (rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) return 0;
            if (rev < Integer.MIN_VALUE / 10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) return 0;
            rev = rev * 10 + pop;
        }
        return rev;
    }

    /**
     * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
     * <p>
     * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
     * <p>
     * Example:
     * <p>
     * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
     * Output: 7 -> 0 -> 8
     * Explanation: 342 + 465 = 807.
     */

    @Test
    public void addTwoNumbers() {
        ListNode l1 = new ListNode(2);
        ListNode l2 = new ListNode(4);
        ListNode l3 = new ListNode(3);

        ListNode l4 = new ListNode(5);
        ListNode l5 = new ListNode(6);
        ListNode l6 = new ListNode(4);
        l2.setNext(l3);
        l1.setNext(l2);


        l5.setNext(l6);
        l4.setNext(l5);

        ListNode result = addTwoNumbers(l1, l4);
        System.out.println("");
    }

    private ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = new ListNode();
        return result;
    }

    private ListNode reverseList(ListNode listNode) {
        ListNode pPeverseHead = null;
        ListNode node = listNode;
        ListNode pPre = null;
        while (node != null) {
            ListNode next = node.next;
            if (next == null) {
                pPeverseHead = node;
            }
            node.next = pPre;
            pPre = node;
            node = next;
        }
        return pPeverseHead;
    }


    public class ListNode {
        int val;
        ListNode next;

        public ListNode() {
        }

        ListNode(int x) {
            val = x;
        }

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }

        public ListNode getNext() {
            return next;
        }

        public void setNext(ListNode next) {
            this.next = next;
        }
    }


    /**
     * Reverse bits of a given 32 bits unsigned integer.
     * Example 1:
     * <p>
     * Input: 00000010100101000001111010011100
     * Output: 00111001011110000010100101000000
     * Explanation: The input binary string 00000010100101000001111010011100 represents the unsigned integer 43261596, so return 964176192 which its binary representation is 00111001011110000010100101000000.
     * Example 2:
     * <p>
     * Input: 11111111111111111111111111111101
     * Output: 10111111111111111111111111111111
     * Explanation: The input binary string 11111111111111111111111111111101 represents the unsigned integer 4294967293, so return 3221225471 which its binary representation is 10101111110010110010011101101001.
     * <p>
     * Note:
     * <p>
     * Note that in some languages such as Java, there is no unsigned integer type. In this case, both input and output will be given as signed integer type and should not affect your implementation, as the internal binary representation of the integer is the same whether it is signed or unsigned.
     * In Java, the compiler represents the signed integers using 2's complement notation. Therefore, in Example 2 above the input represents the signed integer -3 and the output represents the signed integer -1073741825.
     */
    @Test
    public void reverseBits() {

    }

}
