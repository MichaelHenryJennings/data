public class DynamicArray<Item> {
    private Object[] items;
    private int size;
    private static final int MIN_SIZE = 16;

    public DynamicArray() {
        items = new Object[MIN_SIZE];
    }

    public DynamicArray(Item[] values) {
        items = new Object[values.length];
        System.arraycopy(values, 0, items, 0, values.length);
    }

    public Item get(int index) {
        return (Item) items[index];
    }

    public Item set(int index, Item value) {
        Item previous = (Item) items[index];
        items[index] = value;
        return previous;
    }

    public void add(Item value) {
        if (++size > items.length) {
            Object[] copy = new Object[size * 2];
            System.arraycopy(items, 0, copy, 0, size - 1);
            items = copy;
        }
        items[size - 1] = value;
    }

    public void add(int index, Item value) {
        add(null);
        for (int i = size; i > index;) {
            items[i] = items[--i];
        }
        items[index] = value;
    }

    public void push(Item value) {
        add(value);
    }

    public Item remove() {
        if (size == 0) {
            throw new RuntimeException("Cannot remove from empty dynamic array.");
        }
        size--;
        Item removed = (Item) items[size];
        if (size < items.length / 2 && size > MIN_SIZE) {
            Object[] copy = new Object[items.length * 3 / 4];
            System.arraycopy(items, 0, copy, 0, size);
            items = copy;
        }
        return removed;
    }

    public Item remove(int index) {
        Item temp = (Item) items[index];
        for (int i = index; i < size;) {
            items[i] = items[++i];
        }
        remove();
        return temp;
    }

    public Item pop() {
        return remove();
    }

    public Item peek() {
        if (size == 0) {
            throw new RuntimeException("Cannot peek at empty dynamic array.");
        }
        return (Item) items[size - 1];
    }

    public Item[] toArray() {
        Object[] objects = new Object[size];
        System.arraycopy(items, 0, objects, 0, size);
        return (Item[]) objects;
    }

    public int size() {
        return size;
    }

    public String toString() {
        // todo make special char dynamic array?
        DynamicArray<Character> characters = new DynamicArray<>();
        characters.add('{');
        for (int i = 0; i < size; i++) {
            for (char character : items[i].toString().toCharArray()) {
                characters.add(character);
            }
            characters.add(',');
            characters.add(' ');
        }
        characters.remove();
        characters.remove();
        characters.add('}');
        Object[] characterArray = characters.toArray();
        char[] chars = new char[characterArray.length];
        for (int i = 0; i < characterArray.length; i++) {
            chars[i] = (Character) characterArray[i];
        }
        return new String(chars);
    }
}
