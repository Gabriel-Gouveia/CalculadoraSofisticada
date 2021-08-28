import java.util.Scanner;


public class Calculadora{

    /// Essa função resolve um sistema linear pelo método de Gauss, que é um método de cálculo numérico (EC2).
    /// A função recebe por parâmetro um sistema linear de 4 equações e 4 incógnitas em forma de matriz (array bidimensional).
    /// O objetivo é descobrir o valor das incógnitas utilizando os coeficientes e as igualdades. 
    public static void calculaGauss(double[][] matriz)
    {
        double[][] matrizDois = new double[3][4];
        double[][] matrizTres = new double[2][3];
        
        // Calculando valores para a primeira linha da matriz dois.
        matrizDois[0][0] = (matriz[0][0] * matriz[1][1]) - (matriz[1][0] * matriz[0][1]);
        matrizDois[0][1] = (matriz[0][0] * matriz[1][2]) - (matriz[1][0] * matriz[0][2]);
        matrizDois[0][2] = (matriz[0][0] * matriz[1][3]) - (matriz[1][0] * matriz[0][3]);
        matrizDois[0][3] = (matriz[0][0] * matriz[1][4]) - (matriz[1][0] * matriz[0][4]);

        // Calculando valores para a segunda linha da matriz dois.
        matrizDois[1][0] = (matriz[0][0] * matriz[2][1]) - (matriz[2][0] * matriz[0][1]);
        matrizDois[1][1] = (matriz[0][0] * matriz[2][2]) - (matriz[2][0] * matriz[0][2]);
        matrizDois[1][2] = (matriz[0][0] * matriz[2][3]) - (matriz[2][0] * matriz[0][3]);
        matrizDois[1][3] = (matriz[0][0] * matriz[2][4]) - (matriz[2][0] * matriz[0][4]);

        // Calculando valores para a terceira linha da matriz dois.
        matrizDois[2][0] = (matriz[0][0] * matriz[3][1]) - (matriz[3][0] * matriz[0][1]);
        matrizDois[2][1] = (matriz[0][0] * matriz[3][2]) - (matriz[3][0] * matriz[0][2]);
        matrizDois[2][2] = (matriz[0][0] * matriz[3][3]) - (matriz[3][0] * matriz[0][3]);
        matrizDois[2][3] = (matriz[0][0] * matriz[3][4]) - (matriz[3][0] * matriz[0][4]);

        // Agora a matriz dois está preenchida.
        // O objetivo agora é preencher uma matriz menor, a matriz três.

        // Calculando valores para a primeira linha da matriz três.
        matrizTres[0][0] = (matrizDois[0][0] * matrizDois[1][1]) - (matrizDois[1][0] * matrizDois[0][1]);
        matrizTres[0][1] = (matrizDois[0][0] * matrizDois[1][2]) - (matrizDois[1][0] * matrizDois[0][2]);
        matrizTres[0][2] = (matrizDois[0][0] * matrizDois[1][3]) - (matrizDois[1][0] * matrizDois[0][3]);

        // Calculando valores para a segunda linha da matriz três.
        matrizTres[1][0] = (matrizDois[0][0] * matrizDois[2][1]) - (matrizDois[2][0] * matrizDois[0][1]);
        matrizTres[1][1] = (matrizDois[0][0] * matrizDois[2][2]) - (matrizDois[2][0] * matrizDois[0][2]);
        matrizTres[1][2] = (matrizDois[0][0] * matrizDois[2][3]) - (matrizDois[2][0] * matrizDois[0][3]);

        // A matriz três está preenchida.
        // Fazendo a multiplicação em cruz (mas invertendo o sinal dependendo do sentido da multiplicação, como se fosse calcular uma determinante),
        // É possível descobrir o coeficiente de Z e o valor que está no outro lado do sinal de igual.
        // Exemplo: 4Z = 80
        // A varíavel coeficiente no exemplo acima seria o 4 e a variável valorIgual no exemplo seria o 80.

        double coeficiente = (matrizTres[0][0] * matrizTres[1][1]) - (matrizTres[1][0] * matrizTres[0][1]);
        double valorIgual =  (matrizTres[0][0] * matrizTres[1][2]) - (matrizTres[1][0] * matrizTres[0][2]);

        // Verifica se o sistema linear é indeterminado.
        if (coeficiente == 0 && valorIgual == 0)
        {
            System.out.println("O sistema linear eh indeterminado.");
            return;
        }

        // Tendo o coeficiente e o valorIgual, é possível descobrir o valor de Z:

        double z = valorIgual / coeficiente;

        // Para descobrir o valor de Y, basta substituir o Z em uma das equações da matrizTres.
        // Cada linha da matrizTres é uma equação de duas incógnitas. As incógnitas são Y e Z.
        // Os valores nas duas primeiras colunas da matrizTres são coeficientes de incógnitas. A última coluna contém valores de igualdade.

        // Uma linha da matrizTres foi escolhida pelo programador para descobrir o valor de Y.
        // A operação abaixo é uma resolução de uma equação de primeiro grau. A incógnita Z foi substituida pelo seu valor e multiplicou pelo seu coeficiente na equação.
        // Exemplo: A equação era 3Y + 9Z = 15
        // Se o valor de Z é 2, substituindo fica: 3Y + 9 * 2 = 15
        // 3Y + 18 = 15
        // Em seguida, o resultado dessa multiplicação passou para o outro lado do sinal de igual, subtraindo o valor da igualdade pelo resultado da multiplicação.
        // 3Y = 15 -18
        // 3Y = -3
        // Então, 3 estava multiplicando com Y, passou a dividir:
        // Y = -3 / 3
        // Y = -1
        // O comando abaixo simplesmente isola o Y e faz todas as operações descritas acima.
        // matrizTres[0][2] seria o 15 do exemplo. matrizTres[0][1] seria o 9 do exemplo. matrizTres[0][0] seria o 3 do exemplo.
        double y = ((matrizTres[0][2]) - (z * matrizTres[0][1])) / matrizTres[0][0];

        // Para descobir o valor de X, basta escolher uma das equações da matrizDois e substituir o Y e o Z.
        // Vale lembrar que cada linha de uma matriz é uma equação.

        double x = ((matrizDois[0][3]) - (z * matrizDois[0][2]) - (y * matrizDois[0][1])) / matrizDois[0][0];

        // Agora precisa substituir os valores de X, Y e Z em uma das equações da matriz recebida por parâmetro para descobrir o W.

        double w = ((matriz[0][4]) - (z * matriz[0][3]) - (y * matriz[0][2]) - (x * matriz[0][1])) / matriz[0][0];
        // O programador escolheu apenas as primeiras linhas de cada matriz, mas poderia ter sido qualquer linha (qualquer equação) para substituir os valores.

        System.out.println();

        System.out.println("O valor de W eh " + w);
        System.out.println("O valor de X eh " + x);
        System.out.println("O valor de Y eh " + y);
        System.out.println("O valor de Z eh " + z);
        System.out.println();
    }

    public static void imprimeSistemaLinear(double[][] matriz)
    {
        System.out.println();
        System.out.println("Esse eh o sistema linear:");
        System.out.println();
        System.out.println();
        
        for (int linha = 0; linha < 4; linha++)
                    {
                        for (int coluna = 0; coluna < 5; coluna++)
                        {
                            switch (coluna)
                            {
                                case 0:
                                System.out.print(matriz[linha][coluna] + "W ");
                                
                                break;

                                case 1:
                                System.out.print(matriz[linha][coluna] + "X ");
                                
                                break;

                                case 2:
                                System.out.print(matriz[linha][coluna] + "Y ");
                                
                                break;

                                case 3:
                                System.out.print(matriz[linha][coluna] + "Z ");
                                
                                break;

                                case 4:
                                System.out.print("= " + matriz[linha][coluna]);
                                
                                break;
                            }
                        }
                        System.out.println(); // talvez não deveria ser aqui esse comando.
                    }
    }

    /// Essa função calcula o índice de massa corporal, indicador reconhecido pela OMS.
    public static void calculaIMC(double massa, double altura)
    {
        double imc = massa / (altura * altura);

        if (imc < 17)
            System.out.println("Seu IMC eh " + imc + " kg/m2. Voce esta muito abaixo do peso.");

        else if (imc >= 17 && imc <= 18.49)
            System.out.println("Seu IMC eh " + imc + " kg/m2. Voce esta abaixo do peso.");
        
        else if (imc >= 18.5 && imc <= 24.99)
            System.out.println("Seu IMC eh " + imc + " kg/m2. Voce esta com peso normal.");
        
        else if (imc >= 25 && imc <= 29.99)
            System.out.println("Seu IMC eh " + imc + " kg/m2. Voce esta um pouco acima do peso.");

        else if (imc >= 30 && imc <= 34.99)
            System.out.println("Seu IMC eh " + imc + " kg/m2. Voce esta na obesidade I.");

        else if (imc >= 35 && imc <= 39.99)
            System.out.println("Seu IMC eh " + imc + " kg/m2. Voce esta na obesidade II.");

        else 
            System.out.println("Seu IMC eh" + imc + " kg/m2. " + "Voce esta na obesidade III.");
    }

    /// Essa função calcula rendimentos a juros simples.
    public static double[] calculaJuros(double taxa, int periodo, int quantidadeTempo, double valorAplicado)
    {
        // As funções anteriores foram todas void. Nessa função eu resolvi retornar alguma coisa.
        // Vou retornar um vetor double que vai guardar duas informações:
        // dados[0] = O dinheiro final depois de um tempo rendendo;
        // dados[1] = O quanto de dinheiro total rendeu desde quando o rendimento começou. É o rendimento total.
        double[] dados = new double[2];

        // É necessário dividir a taxa por 100 para poder fazer as contas
        taxa = taxa / 100;

        // FV é o valor final após um período rendendo a juros simples.
        double fv = 0;       
               
        // Fórmula de juros simples
        fv = valorAplicado * (1 + taxa * quantidadeTempo);       
            
        dados[0] = fv;
        dados[1] = fv - valorAplicado; 
        
        return dados;
    }

    public static double calculaCorrente(double t, double r)
    {
        return t / r;
    }

    public static double calculaResistencia(double tensao, double corrente)
    {
        return tensao / corrente;
    }

    public static double calculaTensao(double resistencia, double corrente)
    {
        return resistencia * corrente;
    }

    /// Essa função calcula o quanto de energia um objeto elétrico (ex: uma lâmpada) consome em kWh e o quanto esse objeto custa em dinheiro após um mês de uso.
    public static void calculaCustoEletricidade()
    {
        double tarifa, potencia, energia, custo;
        int horas;
        boolean excecao = true;
        Scanner s = new Scanner(System.in);
        do
        {
            try{
                System.out.println("Quanto ta a tarifa do kWh? (A cada 1 kWh que voce consome, voce paga quanto?)");
                tarifa = Double.parseDouble(s.nextLine());
                
                System.out.println("Qual eh a potencia em Watts do objeto?");
                potencia = Double.parseDouble(s.nextLine());

                System.out.println("Quantas horas por dia mais ou menos o objeto fica ligado?");
                horas = Integer.parseInt(s.nextLine());

                excecao = false;

                // Eu quero em kW:
                potencia /= 1000;

                // Potência (W) = Energia (J) / Tempo (s)
                // Energia (kWh) = Potência (W) * Tempo (h)
                energia = potencia * horas;
                custo = energia *tarifa;

                System.out.println("Com esse objeto voce gasta R$ " + custo + " por mes.");
                s.close();


            }

            catch(NumberFormatException e)
            {
                excecao = true;
                System.out.println("Invalido.");
            }

        }while(excecao == true);

    }
    

    public static void calculaCargaCapacitor()
    {
        int carga, tensao, capacitancia;
        boolean excecao = true;
        Scanner s = new Scanner(System.in);
        do
        {
            try
            {
                System.out.println("Digite a carga eletrica armazenada em coulombs(C): ");
                carga = Integer.parseInt(s.nextLine());

                System.out.println("Digite a tensao: ");
                tensao = Integer.parseInt(s.nextLine());

                capacitancia = carga/tensao;
                System.out.println("Sua capacitância é: "+ capacitancia);
                s.close();
                excecao = false;


            }
            catch(Exception e)
            {
                excecao = true;
                System.out.println("Invalido.");
            }
        }while(excecao == true);
    }

    public static void calculaDescargaCapacitor()
    {
        
    }

    public static void converterDistancia(){
        boolean correto = false;
        int opcao = 0;
        Scanner s = new Scanner(System.in);
        do{
            System.out.println("Qual conversão você deseja fazer?");
            System.out.println("1- metros para jardas");
            System.out.println("2- jardas para metros");
            System.out.println("3- menu pricipal");
            opcao = Integer.parseInt(s.nextLine());
            switch(opcao){
                case 1:
                System.out.println("Digite o valor em metros: ");
                double metros = Double.parseDouble(s.nextLine());
                double jardas = metros * 1.09361;
                System.out.println("Seu valor em jardas é de "+ jardas);
                break;
                
                case 2:
                System.out.println("Digite o valor em jardas: ");
                jardas = Double.parseDouble(s.nextLine());
                metros = jardas * 0.9144;
                System.out.println("Seu valor em metros é de: "+ metros);
                break;

                case 3:
                menuConversoes();
                break;

            }
        }while(opcao != 1 || opcao != 2 || opcao != 3);
    }
    public static void converterMassa(){
        boolean correto = false;
        int opcao = 0;
        Scanner s = new Scanner(System.in);
        do{
            System.out.println("Qual conversão você deseja fazer?");
            System.out.println("1- quilos para libras");
            System.out.println("2- libras para quilos");
            System.out.println("3- menu pricipal");
            opcao = Integer.parseInt(s.nextLine());
            switch(opcao){
                case 1:
                System.out.println("Digite o valor em quilos: ");
                double quilos = Double.parseDouble(s.nextLine());
                double libras = quilos * 2.204620823516057;
                System.out.println("Seu valor em libras é de "+ libras);
                break;
                
                case 2:
                System.out.println("Digite o valor em libras: ");
                libras = Double.parseDouble(s.nextLine());
                quilos = libras * 0.453592
                ;
                System.out.println("Seu valor em quilos é de: "+ quilos);
                break;

                case 3:
                menuConversoes();
                break;

            }
        }while(opcao != 1 || opcao != 2 || opcao != 3);
    }

    public static void converterPotencia(){
        boolean correto = false;
        int opcao = 0;
        Scanner s = new Scanner(System.in);
        do{
            System.out.println("Qual conversão você deseja fazer?");
            System.out.println("1- joules para W/h");
            System.out.println("2- W/h para joules");
            System.out.println("3- menu pricipal");
            opcao = Integer.parseInt(s.nextLine());
            switch(opcao){
                case 1:
                System.out.println("Digite o valor em joules: ");
                double joules = Double.parseDouble(s.nextLine());
                double wathora = joules * 0.277778;
                System.out.println("Seu valor em W/h é de "+ wathora);
                break;
                
                case 2:
                System.out.println("Digite o valor em W/h: ");
                wathora = Double.parseDouble(s.nextLine());
                joules = wathora * 3.6 ;
                System.out.println("Seu valor em joules é de: "+ joules);
                break;

                case 3:
                menuConversoes();
                break;

            }
        }while(opcao != 1 || opcao != 2 || opcao != 3);
    }
    
    public static void menuCalculos()
    {
        boolean correto = false;        
        int opcao;
        Scanner s = new Scanner(System.in);
        
        do{
            try{
                do{
                    System.out.println();
                    System.out.println("Menu cálculos");
                    System.out.println("1- Resolucao de um sistema linear pelo dispositivo pratico de Gauss");
                    System.out.println("2- Calculo de IMC");
                    System.out.println("3- Calculo de juros simples");
                    System.out.println("4- Calculo de corrente eletrica");
                    System.out.println("5- Calculo de tensao eletrica");
                    System.out.println("6- Calculo de resistencia eletrica");
                    System.out.println("7- Calculo de potencia eletrica");
                    System.out.println("8- Calculo de custo de eletricidade de um objeto no periodo de um mes");
                    System.out.println("9- Calculo de carga de um capacitor");
                    System.out.println("10- Calculo de descarga de um capacitor");
                    System.out.println("11- Calculo de fatorial");
                    System.out.println("50 - Sair");
                    System.out.println("Por favor, sempre role para cima (no terminal) para ver o resultado de um calculo.");
                    opcao = Integer.parseInt(s.nextLine());
                    correto = true;

                    switch(opcao){

                        case 1:
                        System.out.println("O sistema linear deve possuir 4 equacoes, sendo que cada equacao possui 4 incognitas.");
                        System.out.println("Exemplo: \n 3W - 4X + 8Y - 5Z = 3 \n 2W - 5X + 6Y - 27Z = 52 \n 8W + 3X + 19Y - 36Z = 31 \n 7W - 6X - 4Y + 3Z = 9");
                        System.out.println();
                        double[][] matriz = new double[4][5];
                        int contaEquacao = 1;

                        for (int linha = 0; linha < 4; linha++)
                        {
                            System.out.println(contaEquacao + "a. equacao.");
                            for (int coluna = 0; coluna < 5; coluna++)
                            {
                                switch (coluna)
                                {
                                    case 0:
                                    System.out.println("Digite um valor que multiplique por W.");
                                    matriz[linha][coluna] = Double.parseDouble(s.nextLine());
                                    System.out.println();
                                    break;

                                    case 1:
                                    System.out.println("Digite um valor que multiplique por X.");
                                    matriz[linha][coluna] = Double.parseDouble(s.nextLine());
                                    System.out.println();
                                    break;

                                    case 2:
                                    System.out.println("Digite um valor que multiplique por Y.");
                                    matriz[linha][coluna] = Double.parseDouble(s.nextLine());
                                    System.out.println();
                                    break;

                                    case 3:
                                    System.out.println("Digite um valor que multiplique por Z.");
                                    matriz[linha][coluna] = Double.parseDouble(s.nextLine());
                                    System.out.println();
                                    break;

                                    case 4:
                                    System.out.println("Digite um valor apos o sinal de igual da equacao.");
                                    matriz[linha][coluna] = Double.parseDouble(s.nextLine());
                                    System.out.println();
                                    break;
                                }
                            }
                            contaEquacao++;
                        }
                        imprimeSistemaLinear(matriz);
                        calculaGauss(matriz);
                        break;

                        case 2:
                        double massa, altura;
                        System.out.println("Digite a massa:");
                        massa = Double.parseDouble(s.nextLine());
                        System.out.println("Digite a altura:");
                        altura = Double.parseDouble(s.nextLine());
                        calculaIMC(massa, altura);
                        break;

                        case 3:
                        // taxa é taxa de juros. Vp é valor aplicado para render. Periodo é de quanto em quanto tempo rende (ao ano, ao mês, ao dia).
                        double taxa, vp;
                        int periodo;
                        int qtdTempo;
                        boolean execao = true;
                        System.out.println("Calculo de juros simples sobre o valor aplicado.");
                        System.out.println("Digite a taxa de juros (em porcentagem, sem o simbolo de percentual '%'. Exemplo: 2.9)");
                        taxa = Double.parseDouble(s.nextLine());

                        do {
                        
                            try{

                                do {
                                    System.out.println("A taxa de juros eh \n 1- ao ano \n 2- ao mes \n 3- ao dia");
                                    periodo = Integer.parseInt(s.nextLine());

                                    System.out.println("Digite a quantidade de tempo em que o valor aplicado vai render.");
                                    System.out.println("(Se voce escolheu ano como periodo, entao sera a quantidade de anos. Se voce escolheu mes, sera a quantidade de meses).");
                                    qtdTempo = Integer.parseInt(s.nextLine());
                                    execao = false;

                                    System.out.println("Digite o valor aplicado para render com juros simples");
                                    vp = Double.parseDouble(s.nextLine());

                                    double[] resultados = calculaJuros(taxa, periodo, qtdTempo, vp);

                                    if (periodo == 1)
                                    {
                                        System.out.println("Daqui a " + qtdTempo + " anos voce tera R$ " + resultados[0]);
                                        System.out.println("O dinheiro rendido ate la sera de R$ " + resultados[1] + ".");
                                    }

                                    else if (periodo == 2)
                                    {
                                        System.out.println("Daqui a " + qtdTempo + " meses voce tera R$ " + resultados[0]);
                                        System.out.println("O dinheiro rendido ate la sera de R$ " + resultados[1] + ".");
                                    }

                                    else
                                    {
                                        System.out.println("Daqui a " + qtdTempo + " dias voce tera R$ " + resultados[0]);
                                        System.out.println("O dinheiro rendido ate la sera de R$ " + resultados[1] + ".");
                                    }

                                }while(periodo < 1 || periodo > 3 || vp < 0);
                            }

                            catch (NumberFormatException ex)
                            {
                                execao = true;
                                System.out.println("Invalido.");
                            }
                        }while(execao == true);
                        break;

                        case 4:
                        double tensao, resistencia;

                        System.out.println("Digite a tensao:");
                        tensao = Double.parseDouble(s.nextLine());

                        System.out.println("Digite a resistencia:");
                        resistencia = Double.parseDouble(s.nextLine());

                        double corrente = calculaCorrente(tensao, resistencia);

                        System.out.println("A corrente e " + corrente + "A");

                        break;
                        
                        case 5:
                        double r, t, c;

                        System.out.println("Digite a resistencia:");
                        r = Double.parseDouble(s.nextLine());

                        System.out.println("Digite a corrente:");
                        c = Double.parseDouble(s.nextLine());

                        t = calculaTensao(r, c);

                        System.out.println("A tensao eh " + t + "V");

                        break;

                        case 6:
                        double res, ten, cor;

                        System.out.println("Digite a tensao:");
                        ten = Double.parseDouble(s.nextLine());

                        System.out.println("Digite a corrente:");
                        cor = Double.parseDouble(s.nextLine());

                        res = calculaResistencia(ten, cor);

                        System.out.println("A resistencia eh " + res + "Ohms");

                        break;

                        case 7:
                        
                        double potenciaEletrica, resistenciaEletrica, tensaoEletrica, correnteEletrica;

                        System.out.println("Possui tensao eletrica? [S/N]");
                        String tensaoEletro = s.nextLine();

                        System.out.println("Possui corrente eletrica? [S/N]");
                        String correnteEletro = s.nextLine();

                        System.out.println("Possui resistencia eletrica? [S/N]");
                        String resistenciaEletro = s.nextLine();
                        
                        if (tensaoEletro.toUpperCase().trim() == "S" && correnteEletro.toUpperCase().trim() == "S")
                        {
                            System.out.println("Informe a tensao:");
                            tensaoEletrica = Double.parseDouble(s.nextLine());

                            System.out.println("Informe a corrente:");
                            correnteEletrica = Double.parseDouble(s.nextLine());

                            potenciaEletrica = tensaoEletrica * correnteEletrica;

                            System.out.println("A potencia eh de " + potenciaEletrica + "W");
                        }

                        else if (tensaoEletro.toUpperCase().trim() == "S" && resistenciaEletro.toUpperCase().trim() == "S")
                        {
                            System.out.println("Informe a tensao:");
                            tensaoEletrica = Double.parseDouble(s.nextLine());

                            System.out.println("Informe a resistencia:");
                            resistenciaEletrica = Double.parseDouble(s.nextLine());

                            potenciaEletrica = (tensaoEletrica * tensaoEletrica) / resistenciaEletrica;
                            System.out.println("A potencia eh de " + potenciaEletrica + "W");
                        }

                        else if (resistenciaEletro.toUpperCase().trim() == "S" && correnteEletro.toUpperCase().trim() == "S")
                        {
                            System.out.println("Informe a resistencia:");
                            resistenciaEletrica = Double.parseDouble(s.nextLine());

                            System.out.println("Informe a corrente:");
                            correnteEletrica = Double.parseDouble(s.nextLine());

                            potenciaEletrica = resistenciaEletrica * (correnteEletrica * correnteEletrica);
                            System.out.println("A potencia eh de " + potenciaEletrica + "W");
                        }

                        else
                        {
                            System.out.println("Informacao insuficiente para calcular a potencia eletrica.");
                        }
                        break;

                        case 8:
                        calculaCustoEletricidade();
                        break;
                        
                        case 9:
                        calculaCargaCapacitor();
                        break;

                        case 10:
                        //calculaDescargaCapacitor();
                        break;


                    }

                }while(opcao != 50);
                s.close();
            }              
            
            catch (NumberFormatException ex){
                correto = false;
                System.out.println("Por favor, digite somente numeros. Para opcao, digite apenas numeros inteiros.");
            }

        }while(correto == false);
    }

    public static void menuConversoes()
    {
        boolean correto = false;        
        int opcao= 0;
        Scanner s = new Scanner(System.in);
        do{
            try{
                
                    System.out.println();
                    System.out.println("Menu conversões: ");
                    System.out.println("1- Distância");
                    System.out.println("2- Massa");
                    System.out.println("3- Potência");
                    opcao = Integer.parseInt(s.nextLine());
                    correto = true;
                   switch(opcao){
                       case 1:
                       converterDistancia();
                       break;

                       case 2: 
                       converterMassa();
                       break;

                       case 3:
                       converterPotencia();
                       break;
                       
                    
                   }
                   s.close();
                
            }
            catch(NumberFormatException ex){
                correto = false;
                System.out.println("Por favor, digite somente numeros. Para opcao, digite apenas numeros inteiros.");
            }
        }while(correto == false);
    }

    public static void main(String[] args) {
        boolean correto = false;
        int opcao;
        Scanner s = new Scanner(System.in);
        System.out.println("##CALCULADORA SOFISTICADA##");

        do{
            try{

                System.out.println();
                System.out.println("1- Fazer calculos");
                System.out.println("2- Conversoes");
                opcao = Integer.parseInt(s.nextLine());
                correto = true;

                if (opcao == 1)
                    menuCalculos();
        
                else
                    menuConversoes();
                s.close();

            }

            catch(NumberFormatException ex){
                
                correto = false;
                System.out.println("Opção inválida. Por favor, digite somente números inteiros.");
                
            }

        }while(correto == false);
    } 

          
}