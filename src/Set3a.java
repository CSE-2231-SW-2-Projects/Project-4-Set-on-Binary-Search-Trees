import java.util.Iterator;

import components.binarytree.BinaryTree;
import components.binarytree.BinaryTree1;
import components.set.Set;
import components.set.SetSecondary;

/**
 * {@code Set} represented as a {@code BinaryTree} (maintained as a binary
 * search tree) of elements with implementations of primary methods.
 *
 * @param <T>
 *            type of {@code Set} elements
 * @mathdefinitions <pre>
 * IS_BST(
 *   tree: binary tree of T
 *  ): boolean satisfies
 *  [tree satisfies the binary search tree properties as described in the
 *   slides with the ordering reported by compareTo for T, including that
 *   it has no duplicate labels]
 * </pre>
 * @convention IS_BST($this.tree)
 * @correspondence this = labels($this.tree)
 *
 * @author Zheyuan Gao
 * @author Cedric Fausey
 *
 */
public class Set3a<T extends Comparable<T>> extends SetSecondary<T> {

    /*
     * Private members --------------------------------------------------------
     */

    /**
     * Elements included in {@code this}.
     */
    private BinaryTree<T> tree;

    /**
     * Returns whether {@code x} is in {@code t}.
     *
     * @param <T>
     *            type of {@code BinaryTree} labels
     * @param t
     *            the {@code BinaryTree} to be searched
     * @param x
     *            the label to be searched for
     * @return true if t contains x, false otherwise
     * @requires IS_BST(t)
     * @ensures isInTree = (x is in labels(t))
     */
    private static <T extends Comparable<T>> boolean isInTree(BinaryTree<T> t,
            T x) {
        assert t != null : "Violation of: t is not null";
        assert x != null : "Violation of: x is not null";

        boolean found = false;
        BinaryTree<T> lt = new BinaryTree1<>();
        BinaryTree<T> rt = new BinaryTree1<>();

        /*
         * If t is non-empty tree. We can continue the searching.
         */
        if (t.height() > 0) {
            T root = t.disassemble(lt, rt);
            /*
             * If the root == x, set found = true.
             */
            if (root.equals(x)) {
                found = true;
            }
            /*
             * If the root > x, continue searching the left tree.
             */
            if (root.compareTo(x) > 0) {
                found = isInTree(lt, x);
            }
            /*
             * If the root < x, continue searching the right tree.
             */
            if (root.compareTo(x) < 0) {
                found = isInTree(rt, x);
            }
            /*
             * Restore the tree.
             */
            t.assemble(root, lt, rt);
        }
        /*
         * If the tree is empty, just return found initial value.
         */

        return found;

    }

    /**
     * Inserts {@code x} in {@code t}.
     *
     * @param <T>
     *            type of {@code BinaryTree} labels
     * @param t
     *            the {@code BinaryTree} to be searched
     * @param x
     *            the label to be inserted
     * @aliases reference {@code x}
     * @updates t
     * @requires IS_BST(t) and x is not in labels(t)
     * @ensures IS_BST(t) and labels(t) = labels(#t) union {x}
     */
    private static <T extends Comparable<T>> void insertInTree(BinaryTree<T> t,
            T x) {
        assert t != null : "Violation of: t is not null";
        assert x != null : "Violation of: x is not null";

        BinaryTree<T> lt = new BinaryTree1<>();
        BinaryTree<T> rt = new BinaryTree1<>();
        /*
         * If t is empty, make x the root of the updated t.
         */
        if (t.height() == 0) {
            t.assemble(x, lt, rt);
        } else {
            /*
             * If t is non-empty. Check if x is greater than the root of the t
             * to decide which part of the tree should it be insert.
             */
            T root = t.disassemble(lt, rt);
            if (root.compareTo(x) > 0) {
                /*
                 * If the x is less than the root of t, insert it in the left
                 * tree.
                 */
                insertInTree(lt, x);
            } else {
                /*
                 * If the x is greater than the root of t, insert it in the
                 * right tree.
                 */
                insertInTree(rt, x);
            }
            /*
             * Update and assemble the original tree.
             */
            t.assemble(root, lt, rt);
        }
    }

    /**
     * Removes and returns the smallest (left-most) label in {@code t}.
     *
     * @param <T>
     *            type of {@code BinaryTree} labels
     * @param t
     *            the {@code BinaryTree} from which to remove the label
     * @return the smallest label in the given {@code BinaryTree}
     * @updates t
     * @requires IS_BST(t) and |t| > 0
     * @ensures <pre>
     * IS_BST(t)  and  removeSmallest = [the smallest label in #t]  and
     *  labels(t) = labels(#t) \ {removeSmallest}
     * </pre>
     */
    private static <T> T removeSmallest(BinaryTree<T> t) {
        assert t != null : "Violation of: t is not null";

        T smallest;
        BinaryTree<T> lt = new BinaryTree1<>();
        BinaryTree<T> rt = new BinaryTree1<>();
        /*
         * First disassemble the t.
         */
        T root = t.disassemble(lt, rt);

        if (lt.height() > 0) {
            /*
             * If the root have a non-empty left subtree, then remove the
             * smallest label from the left subtree.
             */
            smallest = removeSmallest(lt);
            /*
             * Update and assemble the original tree.
             */
            t.assemble(root, lt, rt);
        } else {
            /*
             * If the root have no left subtree, then the root is the smallest
             * label of t. Make the right tree the new value of t and return the
             * root.
             */
            smallest = root;
            t.transferFrom(rt);
        }

        return smallest;

    }

    /**
     * Finds label {@code x} in {@code t}, removes it from {@code t}, and
     * returns it.
     *
     * @param <T>
     *            type of {@code BinaryTree} labels
     * @param t
     *            the {@code BinaryTree} from which to remove label {@code x}
     * @param x
     *            the label to be removed
     * @return the removed label
     * @updates t
     * @requires IS_BST(t) and x is in labels(t)
     * @ensures <pre>
     * IS_BST(t)  and  removeFromTree = x  and
     *  labels(t) = labels(#t) \ {x}
     * </pre>
     */
    private static <T extends Comparable<T>> T removeFromTree(BinaryTree<T> t,
            T x) {
        assert t != null : "Violation of: t is not null";
        assert x != null : "Violation of: x is not null";

        T target;
        BinaryTree<T> lt = new BinaryTree1<>();
        BinaryTree<T> rt = new BinaryTree1<>();
        T root = t.disassemble(lt, rt);

        /*
         * If the root != x, then search the subtrees for x.
         */
        if (!root.equals(x)) {

            if (x.compareTo(root) < 0) {
                /*
                 * If the root is greater than x, then search the target in the
                 * left subtree.
                 */
                target = removeFromTree(lt, x);
            } else {
                /*
                 * If the root is less than x, then search the target in the
                 * right subtree.
                 */
                target = removeFromTree(rt, x);

            }
            /*
             * Update and assemble the original tree.
             */
            t.assemble(root, lt, rt);

        } else {
            target = root;
            /*
             * If root == r, we have two choice: 1. If right tree is non-empty,
             * make the its smallest label to be the mew root of the t. 2. If
             * the right tree is empty, make the left subtree be the new t.
             */
            if (rt.height() > 0) {
                /*
                 * If right tree is non-empty, make the its smallest label to be
                 * the mew root of the t.
                 */
                t.assemble(removeSmallest(rt), lt, rt);
            } else {
                /*
                 * If the right tree is empty, make the left subtree be the new
                 * t.
                 */
                t.transferFrom(lt);
            }

        }

        return target;

    }

    /**
     * Creator of initial representation.
     */
    private void createNewRep() {

        this.tree = new BinaryTree1<T>();

    }

    /*
     * Constructors -----------------------------------------------------------
     */

    /**
     * No-argument constructor.
     */
    public Set3a() {

        this.createNewRep();

    }

    /*
     * Standard methods -------------------------------------------------------
     */

    @SuppressWarnings("unchecked")
    @Override
    public final Set<T> newInstance() {
        try {
            return this.getClass().getConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            throw new AssertionError(
                    "Cannot construct object of type " + this.getClass());
        }
    }

    @Override
    public final void clear() {
        this.createNewRep();
    }

    @Override
    public final void transferFrom(Set<T> source) {
        assert source != null : "Violation of: source is not null";
        assert source != this : "Violation of: source is not this";
        assert source instanceof Set3a<?> : ""
                + "Violation of: source is of dynamic type Set3<?>";
        /*
         * This cast cannot fail since the assert above would have stopped
         * execution in that case: source must be of dynamic type Set3a<?>, and
         * the ? must be T or the call would not have compiled.
         */
        Set3a<T> localSource = (Set3a<T>) source;
        this.tree = localSource.tree;
        localSource.createNewRep();
    }

    /*
     * Kernel methods ---------------------------------------------------------
     */

    @Override
    public final void add(T x) {
        assert x != null : "Violation of: x is not null";
        assert !this.contains(x) : "Violation of: x is not in this";

        insertInTree(this.tree, x);

    }

    @Override
    public final T remove(T x) {
        assert x != null : "Violation of: x is not null";
        assert this.contains(x) : "Violation of: x is in this";

        return removeFromTree(this.tree, x);
    }

    @Override
    public final T removeAny() {
        assert this.size() > 0 : "Violation of: this /= empty_set";

        return removeSmallest(this.tree);
    }

    @Override
    public final boolean contains(T x) {
        assert x != null : "Violation of: x is not null";

        return isInTree(this.tree, x);
    }

    @Override
    public final int size() {

        return this.tree.size();
    }

    @Override
    public final Iterator<T> iterator() {
        return this.tree.iterator();
    }

}
