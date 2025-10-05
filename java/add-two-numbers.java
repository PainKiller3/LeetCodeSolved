// Problem Link: https://leetcode.com/problems/add-two-numbers/

// Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }

    // Deserialize method: converts a string representation like "2 -> 4 -> 3" into a ListNode
    public static ListNode deserialize(String s) {
        // Remove the square brackets and spaces, then split by commas
        s = s.replaceAll("[\\[\\] ]", "");  // Remove any square brackets and spaces
        String[] values = s.split(",");
        
        ListNode dummyHead = new ListNode(0);
        ListNode current = dummyHead;

        for (String value : values) {
            current.next = new ListNode(Integer.parseInt(value));
            current = current.next;
        }

        return dummyHead.next;
    }

    // Helper method to print the ListNode (for testing purposes)
    public static void printList(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val);
            if (current.next != null) System.out.print(" -> ");
            current = current.next;
        }
        System.out.println();
    }
}

class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // Initialize a dummy node to simplify handling of the result list.
        ListNode dummyHead = new ListNode(0);
        ListNode current = dummyHead;
        int carry = 0;

        // Traverse both linked lists
        while (l1 != null || l2 != null || carry != 0) {
            // Get the values for the current nodes, default to 0 if no more nodes.
            int x = (l1 != null) ? l1.val : 0;
            int y = (l2 != null) ? l2.val : 0;

            // Calculate the sum and the carry
            int sum = x + y + carry;
            carry = sum / 10;  // Update carry for the next iteration.
            current.next = new ListNode(sum % 10);  // Create a new node with the sum's last digit.
            current = current.next;  // Move to the next node in the result list.

            // Move to the next nodes in l1 and l2 if possible.
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }

        // The result is stored in the next of dummyHead.
        return dummyHead.next;
    }
}

public class Main {
    public static void main(String[] args) {
        // Example input: "[2,4,3]" and "[5,6,4]"
        // Removing the square brackets and spaces, it becomes "2,4,3" and "5,6,4"
        ListNode l1 = ListNode.deserialize("2,4,3"); // Represents the number 342
        ListNode l2 = ListNode.deserialize("5,6,4"); // Represents the number 465

        // Running the solution
        Solution solution = new Solution();
        ListNode result = solution.addTwoNumbers(l1, l2);

        // Printing the result (should be 7 -> 0 -> 8)
        ListNode.printList(result);
    }
}
