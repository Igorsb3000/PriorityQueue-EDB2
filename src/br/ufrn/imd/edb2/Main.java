package br.ufrn.imd.edb2;

/**
 * @author Igor Silva
 * @since 2019.2
 * @version 1.1.0
 *
 * Heap Maximo - Fila de Prioridade
 *
 */
public class Main {

    public static void main(String[] args) {
	    //HEAP - Priority Queue
        FilaBanco fila = new FilaBanco();
        Pessoa igor = new Pessoa("Igor", 22);
        fila.addPessoa(igor);
        fila.addPessoa("Fulano", 20);
        fila.addPessoa("Sicrano", 10);
        fila.addPessoa("Vovó", 64);
        Pessoa tio = new Pessoa("Tio", 50);
        fila.addPessoa(tio);
        fila.addPessoa(new Pessoa("Beltrano", 5));
        Pessoa tiao = new Pessoa("Tiao", 51);
        fila.addPessoa(tiao);

        tio.setIdade(52);

        while(fila.getSize() > 0){
            Pessoa p = fila.peek();
            System.out.println(p.getNome() +  " está sendo atendido");
            fila.remove();
        }

    }
}
