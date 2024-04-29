package sptech.school.projetovolt.utils;

@SuppressWarnings("unchecked")
public class ListaObj<T> {

    protected T[] arr;
    private int elements;

    public ListaObj(int size) {
        arr = (T[]) new Object[size];
        elements = 0;
    }

    public T[] getArr() {
        return arr;
    }

    public void setArr(T[] arr) {
        this.arr = arr;
    }

    public int getElements() {
        return elements;
    }

    public void setElements(int elements) {
        this.elements = elements;
    }

    public void add (T e) {
        if (getElements() == getArr().length) {
            throw new IllegalStateException("Lista Cheia");
        }
        arr[elements] = e;
        setElements(getElements() + 1);
    }

    public void show () {
        if (getElements() == 0) {
            System.out.println("[]");
        } else {
            StringBuilder stringBuilder = new StringBuilder("[");
            for (int i = 0; i < getElements(); i++) {
                if (i == getElements() - 1) {
                    stringBuilder.append(getArr()[i]).append("]");
                } else {
                    stringBuilder.append(getArr()[i]).append(", ");
                }
            }
            System.out.println(stringBuilder);
        }
    }

    public int indexOf(T e) {
        for (int i = 0; i < getArr().length; i++) {
            if (getArr()[i] == e) {
                return i;
            }
        }
        return -1;
    }

    public boolean removeByElement (T e) {

        int pos = indexOf(e);

        if (pos != -1) {
            T[] auxArr = getArr().clone();
            setArr((T[]) new Object[getArr().length]);

            setElements(getElements() - 1);

            for (int i = 0; i < getElements(); i++) {
                if (i != pos) {
                    getArr()[i] = auxArr[i];
                } else {
                    getArr()[i] = auxArr[i + 1];
                    pos++;
                }
            }
            return true;
        }

        return false;
    }

    public boolean removeByIndex (int index) {

        if (index < 0 || index >= getElements()) {
            return false;
        }
        return removeByElement(getArr()[index]);
    }

    public boolean set (T oldValue, T newValue) { // a)
        int index = indexOf(oldValue);
        if (index != -1) {
            getArr()[index] = newValue;
            return true;
        }
        return false;
    }

    public int count (T e) { // b)
        int count = 0;
        for (T i : getArr()) {
            if (i.equals(e)) {
                count++;
            }
        }
        return count;
    }

    public boolean addOnZero (T e) { // c)
        if (getElements() == getArr().length) {
            System.out.println("Lista Cheia!");
            return false;
        }

        for (int i = getElements(); i >= 1; i--) {
            getArr()[i] = getArr()[i - 1];
        }

        getArr()[0] = e;
        setElements(getElements() + 1);
        return true;

    }

    public int size () {
        return getElements();
    }

    public T get(int index) {
        if (index >= 0 && index < getElements()) {
            return getArr()[index];
        }
        return null;
    }

    public void clear () {
        setElements(0);
        int size = getArr().length;
        setArr((T[]) new Object[size]);
    }

}

