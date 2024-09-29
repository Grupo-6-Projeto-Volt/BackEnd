package sptech.school.projetovolt.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HashTableObjTest {
    private HashTableObj<String> hashTable;
    @BeforeEach
    void setUp(){hashTable = new HashTableObj<>();}

    @Test
    @DisplayName("Adicionar elementos na hash table")
    void put(){
        hashTable.put("Caixa");
        hashTable.put("Caixa-Vermelha");
        hashTable.put("Caixa-Azul");
        hashTable.put("Caixa-Preta");
        assertEquals(false,hashTable.isEmpty());
        assertEquals("Caixa-Preta",hashTable.get("Caixa-Preta"));
        assertEquals("Caixa-Vermelha",hashTable.get("Caixa-Vermelha"));
        assertEquals("Caixa",hashTable.get("Caixa"));
    }
    @Test
    @DisplayName("Buscar elementos na hash table")
    void get(){
        hashTable.put("Caixa");
        hashTable.put("Caixa-Vermelha");
        hashTable.put("Caixa-Azul");
        hashTable.put("Caixa-Preta");
        assertEquals("Caixa",hashTable.get("Caixa"));
        assertEquals(null,hashTable.get("Caixa-Colorida"));
    }
    @Test
    @DisplayName("Mostrar elementos presentes na hash table")
    void show(){
        hashTable.put("Caixa");
        hashTable.put("Caixa");
        hashTable.put("Caixa");
        hashTable.put("Caixa");

        java.io.ByteArrayOutputStream outContent = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(outContent));
        hashTable.show();
        assertEquals("Entrada 18 :Caixa|Caixa|Caixa|Caixa|",outContent.toString().trim());
    }

    @Test
    @DisplayName("Mostrar se hash table esta vazia")
    void isEmpty(){
        hashTable.put("Caixa");
        hashTable.put("Caixa");
        hashTable.put("Caixa");
        hashTable.put("Caixa");
        assertFalse(hashTable.isEmpty());
    }
    @Test
    @DisplayName("Remover elementos da hash table")
    void remove(){
        hashTable.put("Caixa");
        hashTable.put("Caixa");
        hashTable.put("Caixa");
        hashTable.put("Caixa");

        int[] aux = hashTable.size();

        assertEquals(4,aux[18]);
        assertTrue(hashTable.remove("Caixa"));
        assertEquals(3,aux[18]);
    }
    @Test
    @DisplayName("Mostrar quantidade de elementos em cada entrada")
    void size(){
        hashTable.put("Caixa");
        hashTable.put("Caixa");
        hashTable.put("Caixa");
        hashTable.put("Caixa");

        int[] aux = hashTable.size();
        assertEquals(4,aux[18]);
        assertEquals(0,aux[0]);

    }
}
