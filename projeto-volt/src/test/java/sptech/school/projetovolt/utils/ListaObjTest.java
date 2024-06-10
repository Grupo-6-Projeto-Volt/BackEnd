package sptech.school.projetovolt.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ListaObjTest {

    private ListaObj<String> lista;

    @BeforeEach
    void setUp() {
        lista = new ListaObj<>(5);
    }

    @Test
    @DisplayName("Adicionar elementos na lista")
    void add() {
        lista.add("A");
        lista.add("B");
        lista.add("C");
        assertEquals(3, lista.getElements());
        assertEquals("A", lista.get(0));
        assertEquals("B", lista.get(1));
        assertEquals("C", lista.get(2));
    }

    @Test
    @DisplayName("Adicionar elementos além da capacidade da lista deve lançar exceção")
    void addBeyondCapacity() {
        lista.add("A");
        lista.add("B");
        lista.add("C");
        lista.add("D");
        lista.add("E");
        assertThrows(IllegalStateException.class, () -> lista.add("F"));
    }

    @Test
    @DisplayName("Exibir elementos da lista")
    void show() {
        lista.add("A");
        lista.add("B");
        lista.add("C");
        // Redireciona a saída padrão para um ByteArrayOutputStream
        java.io.ByteArrayOutputStream outContent = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(outContent));
        lista.show();
        assertEquals("[A, B, C]", outContent.toString().trim());
    }

    @Test
    @DisplayName("Buscar índice de elemento na lista")
    void indexOf() {
        lista.add("A");
        lista.add("B");
        lista.add("C");
        assertEquals(1, lista.indexOf("B"));
        assertEquals(-1, lista.indexOf("D"));
    }

    @Test
    @DisplayName("Remover elemento por valor da lista")
    void removeByElement() {
        lista.add("A");
        lista.add("B");
        lista.add("C");
        assertTrue(lista.removeByElement("B"));
        assertEquals(2, lista.getElements());
        assertEquals(-1, lista.indexOf("B"));
    }

    @Test
    @DisplayName("Remover elemento por índice da lista")
    void removeByIndex() {
        lista.add("A");
        lista.add("B");
        lista.add("C");
        assertTrue(lista.removeByIndex(1));
        assertEquals(2, lista.getElements());
        assertEquals(-1, lista.indexOf("B"));
    }

    @Test
    @DisplayName("Atualizar elemento da lista")
    void set() {
        lista.add("A");
        lista.add("B");
        lista.add("C");
        assertTrue(lista.set("B", "D"));
        assertEquals("D", lista.get(1));
        assertFalse(lista.set("E", "F"));
    }

    // TODO: Corrigir o metodo cont
//    @Test
//    @DisplayName("Contar ocorrências de um elemento na lista")
//    void count() {
//        lista.add("A");
//        lista.add("B");
//        lista.add("A");
//        assertEquals(2, lista.count("A"));
//        assertEquals(1, lista.count("B"));
//        assertEquals(0, lista.count("C"));
//    }

    @Test
    @DisplayName("Adicionar elemento no início da lista")
    void addOnZero() {
        lista.add("A");
        lista.add("B");
        lista.add("C");
        assertTrue(lista.addOnZero("D"));
        assertEquals(4, lista.getElements());
        assertEquals("D", lista.get(0));
    }

    @Test
    @DisplayName("Obter tamanho da lista")
    void size() {
        lista.add("A");
        lista.add("B");
        assertEquals(2, lista.size());
    }

    @Test
    @DisplayName("Obter elemento por índice da lista")
    void get() {
        lista.add("A");
        lista.add("B");
        assertEquals("A", lista.get(0));
        assertNull(lista.get(2));
    }

    @Test
    @DisplayName("Limpar a lista")
    void clear() {
        lista.add("A");
        lista.add("B");
        lista.clear();
        assertEquals(0, lista.getElements());
        assertNull(lista.get(0));
    }
}
