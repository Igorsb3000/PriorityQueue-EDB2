package br.ufrn.imd.edb2;

public class Pessoa {
    private Observer observer;
    private String nome;
    private int idade;

    public Pessoa(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        int tmp = this.getIdade();
        this.idade = idade;
        this.observer.update(tmp, observer, this); //update(this.observadores.get(0), this);
    }

    public void addObserver(Observer observer){
        this.observer = observer;
    }


}
