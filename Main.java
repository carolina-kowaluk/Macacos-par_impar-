import java.io.BufferedReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
public class Principal {
    public static void main (String []args){
        long startTime = System.currentTimeMillis();
        int rodadas = 0; //nro de rodadas
        int countLinhas = 0; //nro linhas
        int countColunas = 0; //nro colunas
        int countRodadas=0; 
        int macacoPar = 0;
        int macacoImpar = 0; 
        int tamanhoVetor = 5;      
        ArrayList<int[]> matriz = new ArrayList<int[]>(tamanhoVetor);
        ArrayList<String> linhas = new ArrayList<>(); //linhas da matriz
        String palavras[]; //palavras da linha
        BufferedReader reader;
        Path path1 = Paths.get("caso_1000Macacos_100000Rodadas.txt");

        //carrega as linhas
        try {
            reader = Files.newBufferedReader(path1, Charset.defaultCharset());
            String line = null;
            while( (line = reader.readLine()) != null) {
                linhas.add(line);
            }
                   
            for(String l : linhas){ //percorre as linhas
                palavras = l.split(" "); // divide a string pelo espaco em branco
                countColunas=0;
                if (countLinhas==0){ //primeira linha, rodadas a fazer
                    for(int i =0; i< palavras.length; i++) {
                        String teste= palavras[i] + "";
                        if (teste.matches("[0-9]+")){
                            rodadas = Integer.parseInt(teste);
                        }
                    }
                }
                else{ //se não for a primeira linha
                    matriz.add(new int[]{0,0,0,0,0});        
                    for(int i=0; i < palavras.length; i++){
                        String test= palavras[i] + "";                    
                        if(test!=""){  //se string não estiver vazia                        
                            if(test.matches("[0-9]+")){  //se string for número                              
                                if(countColunas==0 || countColunas==1 || countColunas==2){
                                    matriz.get(countLinhas-1)[countColunas] = Integer.parseInt(test);
                                } 
                                else{
                                    if(par(Integer.parseInt(test))==true){
                                        matriz.get(countLinhas-1)[3] += 1; //soma número de pares
                                    }
                                    else{
                                        matriz.get(countLinhas-1)[4] += 1; //soma número de impares
                                    }
                                }
                                countColunas++;
                            }
                        }
                    }
                }
                countLinhas++;
            }                               
            reader.close();
        
        } catch (Exception e) {
            System.err.format("Erro na leitura do arquivo ", e);
        }

        while(countRodadas<rodadas){ //enquanto o contador for menor que o número de rodadas          
            for(int i=0; i<matriz.size(); i++){             
                for(int j=0; j<matriz.get(i).length; j++){                                     
                    if(j==0){
                        //não faz nada                      
                    }
                    else if(j==1){
                        //macaco par
                        macacoPar = matriz.get(i)[j]; 
                    }
                    else if(j==2){
                        //macaco impar
                        macacoImpar = matriz.get(i)[j];
                    }                  
                    else{
                        matriz.get(macacoPar)[3] += matriz.get(i)[3]; //soma quantidade de pares no macaco par
                        matriz.get(i)[3] = 0; //zera quantidade de pares no macaco origem

                        matriz.get(macacoImpar)[4] += matriz.get(i)[4]; //soma quanidade de impares no macaco impar
                        matriz.get(i)[4] = 0; //zera quantidade de impares no macaco origem
                    }                                                      
                }                   
            }           
            countRodadas++;
        }

        //verificar qual macaco ganhou
        System.out.println("Ganhador: macaco "+ganhador(matriz));
        //verifica tempo de execução
        long endTime = System.currentTimeMillis();
        long timeElapsed = endTime - startTime;
        System.out.println("Execution time in seconds: " + timeElapsed/1000);
        
    }

    public static int ganhador(ArrayList<int[]> matriz){
        int tamanho = 0;
        int macaco = 0;
        for(int i=0; i<matriz.size(); i++){
            if(tamanho<matriz.get(i)[3] + matriz.get(i)[4]){
                tamanho = matriz.get(i)[3] + matriz.get(i)[4]; 
                macaco = i;   
            }           
        }
        return macaco;
    }

    public static boolean par(int num){
        boolean par = false;              
        if (num%2==0){ par = true;}              
        return par;
    }
}
