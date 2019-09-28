package br.ufrn.imd.edb2;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FilaBancoTest {

    FilaBanco fila = null;

    @Before//executa antes de cada teste
    public void init(){
        fila = new FilaBanco();
    }

    @Test
    public void mustBeOrdered(){
        //Arrange
        Pessoa vovo = new Pessoa("Vovó", 65);
        Pessoa fulano = new Pessoa("Fulano", 10);
        Pessoa sicrano = new Pessoa("Sicrano", 25);

        //Act
        fila.addPessoa(fulano);
        fila.addPessoa(vovo);
        fila.addPessoa(sicrano);

        //Assert
        assertSame(vovo, fila.peek());
        fila.remove();
        assertSame(sicrano, fila.peek());
        fila.remove();
        assertSame(fulano, fila.peek());
        fila.remove();
        assertNull(fila.peek());
    }

    @Test
    public void mustReorderWhenPriorityChange(){
        //Arrange
        Pessoa vovo = new Pessoa("Vovó", 65);
        Pessoa fulano = new Pessoa("Fulano", 10);
        Pessoa sicrano = new Pessoa("Sicrano", 25);
        fila.addPessoa(vovo);
        fila.addPessoa(fulano);
        fila.addPessoa(sicrano);

        //Act
        fulano.setIdade(67);

        //Assert
        assertSame(fulano, fila.peek());
    }

    @Test
    public void mustReorderWhenPriorityChangeWithHeapifyUp(){
        //Arrange
        Pessoa vovo = new Pessoa("Vovó", 65);
        Pessoa fulano = new Pessoa("Fulano", 10);
        Pessoa sicrano = new Pessoa("Sicrano", 25);
        Pessoa tio = new Pessoa("Tio", 52);
        fila.addPessoa(tio);
        fila.addPessoa(vovo);
        fila.addPessoa(fulano);
        fila.addPessoa(sicrano);

        //Act
        tio.setIdade(67);

        //Assert
        assertSame(tio, fila.peek());
        fila.remove();
        assertSame(vovo, fila.peek());
        fila.remove();
        assertSame(sicrano, fila.peek());
        fila.remove();
        assertSame(fulano, fila.peek());
        fila.remove();
        assertNull(fila.peek());
    }

    @Test
    public void mustReorderWhenPriorityChangeWithHeapifyDown(){
        //Arrange
        Pessoa vovo = new Pessoa("Vovó", 65);
        Pessoa fulano = new Pessoa("Fulano", 10);
        Pessoa sicrano = new Pessoa("Sicrano", 25);
        Pessoa tio = new Pessoa("Tio", 52);
        fila.addPessoa(tio);
        fila.addPessoa(vovo);
        fila.addPessoa(fulano);
        fila.addPessoa(sicrano);

        //Act
        tio.setIdade(22);

        //Assert
        assertSame(vovo, fila.peek());
        fila.remove();
        assertSame(sicrano, fila.peek());
        fila.remove();
        assertSame(tio, fila.peek());
        fila.remove();
        assertSame(fulano, fila.peek());
        fila.remove();
        assertNull(fila.peek());
    }

    @Test
    public void peekMustReturnFirstElementHighestPriority(){
        //Arrange
        Pessoa node = new Pessoa("Fulano", 10);

        //Act
        fila.addPessoa(node);

        //Assert
        assertSame(node, fila.peek()); //compara a posicao de memoria
    }

    @Test
    public void mustBeInitializedEmpty(){
        //Assert
        assertNull(fila.peek());
    }

}