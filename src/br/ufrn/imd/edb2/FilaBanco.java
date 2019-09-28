package br.ufrn.imd.edb2;

import java.util.Arrays;

public class FilaBanco implements Observer{
    private Pessoa[] pessoas;
    private int size; //quantos elementos tem
    private int capacity;//quantos elementos pode ter

    public FilaBanco(){
        this(10); //chama o outro construtor(mesma classe)
    }

    public FilaBanco(int capacity){
        this.pessoas = new Pessoa[capacity];
        this.size = 0;
        this.capacity = capacity;
    }

    public void addPessoa(String nome, int idade) {
        addPessoa((new Pessoa(nome, idade)));
    }

    public void addPessoa(Pessoa pessoa){
        pessoa.addObserver(this); //adicionando essa FilaBanco como observador dessa pessoa
        this.ensureCapacity(); //verifica se size == capacity, se iguais entao dobra a capacidade do Array
        this.pessoas[getSize()] = pessoa;
        heapifyUp(getSize());
        size++;
    }

    private void heapifyUp(int index){ //compara o elemento com pai até que o pai seja maior
        if(!hasParent(index)){
            return; //nao tem onde ele subir mais
        }
        int parentIndex = getParentIndex(index);
        Pessoa node = pessoas[index];
        Pessoa pai = pessoas[parentIndex];

        if(node.getIdade() > pai.getIdade()){
            pessoas[index] = pai;
            pessoas[parentIndex] = node;
            heapifyUp(parentIndex);
        }

    }

    private boolean hasParent(int index){
        return getParentIndex(index) >= 0 && getParentIndex(index) < size;
    }

    private int getParentIndex(int index) {
        return (int) Math.floor((index - 1) / 2);
    }

    private void ensureCapacity() {
        if(getSize() == capacity){
            this.pessoas = Arrays.copyOf(this.pessoas, getSize()*2); //copia o array dobrando seu tamanho
            capacity = getSize()*2;
        }
    }

    public int getSize() {
        return size;
    }

    public Pessoa peek() {
        if(getSize() == 0){
            return null;
        }
        return pessoas[0];
    }

    public void remove() {
        pessoas[0] = pessoas[getSize()-1]; //pessoa é mandada para o no raiz
        pessoas[getSize()-1] = null;
        size--;
        heapifyDown(0); //verificando o msm que heapifyUp
    }

    private void heapifyDown(int index) {
        int leftChild = index * 2 + 1;
        int rightChild = index * 2 + 2;
        int childIndex = -1;

        if(leftChild < getSize()){
             //filho da esquerda existe
            childIndex = leftChild;
        }
        if(childIndex < 0){ //childIndex == -1
            return;
        }

        if(rightChild < getSize()){
            //filho da direita existe
            if(pessoas[rightChild].getIdade() > pessoas[leftChild].getIdade()){
                childIndex = rightChild;
            }
        }

        if(pessoas[index].getIdade() < pessoas[childIndex].getIdade()){ //apenas se a idade é menor que a do filho
            Pessoa tmp = pessoas[index];
            pessoas[index] = pessoas[childIndex];
            pessoas[childIndex] = tmp;
            heapifyDown(childIndex); //checa novamente
        }

    }

    @Override
    public void update(int idadeAntiga, Observer observer, Pessoa pessoa) {
        FilaBanco fila = (FilaBanco) observer;
        int index = -1;
        for(int i = 0; i < fila.getSize(); i++){
            if(fila.pessoas[i].getNome() == pessoa.getNome() && fila.pessoas[i].getIdade() == pessoa.getIdade()){
                index = i;
                break;
            }
        }
        if(pessoa.getIdade() < idadeAntiga){ //idade diminuiu
            fila.heapifyDown(index);
        }
        //idade aumentou
        fila.heapifyUp(index);
    }


}
