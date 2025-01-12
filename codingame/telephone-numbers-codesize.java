import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int countNodes = 0;

        ArrayList<PhoneNode> phoneBook = new ArrayList<>(); // list of first nodes

        String[] telephones = new String[N];

        for (int i = 0; i < N; i++) {
            telephones[i] = in.next();
        }

        Arrays.sort(telephones); // all the phones sorted in an array (string)

        for (String telephone : telephones) {
            char[] telephoneCharArray = telephone.toCharArray();

            PhoneNode referenceNode = null;

            for (PhoneNode node : phoneBook)
                if (node.getNumber() == telephoneCharArray[0]) 
                    referenceNode = node;

            if (referenceNode == null) {
                referenceNode = new PhoneNode(telephoneCharArray[0]);
                countNodes++;
                phoneBook.add(referenceNode);
            }

            for (int i = 1; i < telephoneCharArray.length ; i++) {
                PhoneNode searchNode = referenceNode.findChildrenWithNumber(telephoneCharArray[i]);
                if (searchNode != null) {
                    referenceNode = searchNode;
                } else {
                    PhoneNode newNode = new PhoneNode(telephoneCharArray[i]);
                    referenceNode.addChildren(newNode);
                    countNodes++;
                    referenceNode = newNode;
                }
            }
        }
        
        // The number of elements (referencing a number) stored in the structure.
        System.out.println(countNodes);

        in.close();
    }
}

class PhoneNode {
    private PhoneNode parent;
    private ArrayList<PhoneNode> childrens;
    private char number;

    public PhoneNode () {

    }

    public PhoneNode (char number) {
        this.number = number;
    }

    public void setNumber(char number) {
        this.number = number;
    }

    public char getNumber() {
        return this.number;
    }

    public void setParent(PhoneNode parent) {
        this.parent = parent;
    }

    public PhoneNode getParent() {
        return this.parent;
    }
 
    public void addChildren(PhoneNode children) {
        if (childrens == null)
            childrens = new ArrayList<>();
        childrens.add(children);
    }

    public ArrayList<PhoneNode> getChildrens() {
        return this.childrens;
    }

    public PhoneNode findChildrenWithNumber(char number) {
        if (childrens != null)
            for (PhoneNode children : childrens) {
                if (children.getNumber() == number) 
                    return children;
            }

        return null;
    }
}
