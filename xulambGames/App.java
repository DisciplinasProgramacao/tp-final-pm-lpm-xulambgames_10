package xulambGames;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Scanner;

import javax.management.InvalidAttributeValueException;

import java.text.DecimalFormat;
import java.time.LocalDate;

public class App {

    static Scanner teclado;
    static String arqDadosCliente = "Clientes.bin";
    static String arqDadosJogos = "Jogos.bin";
    static String arqDadosCompras = "Compras.bin";
    static ArrayList<Cliente> listaClientes;
    static ArrayList<Jogo> listaJogos;
    static ArrayList<Compra> listaCompras;
    static GerenciaXulambs gerencia;

    public static void gravarDados() throws IOException {
        ObjectOutputStream obj = new ObjectOutputStream(new FileOutputStream(arqDadosCliente));
        if (listaClientes.size() > 0) {
            for (Cliente cliente : listaClientes) {
                obj.writeObject(cliente);
            }
        }
        obj.close();
        obj = new ObjectOutputStream(new FileOutputStream(arqDadosJogos));
        if (listaJogos.size() > 0) {
            for (Jogo jogo : listaJogos) {
                obj.writeObject(jogo);
            }
        }
        obj.close();
        obj = new ObjectOutputStream(new FileOutputStream(arqDadosCompras));
        if (listaCompras.size() > 0) {
            for (Compra compra : listaCompras) {
                obj.writeObject(compra);
            }
        }
        obj.close();
    }

    public static void carregarDados(Scanner teclado) {
        FileInputStream dados;
        ArrayList<Cliente> clientes = new ArrayList<Cliente>();
        ArrayList<Jogo> jogos = new ArrayList<Jogo>();
        ArrayList<Compra> compras = new ArrayList<Compra>();

        try {
            dados = new FileInputStream(arqDadosCliente);
            ObjectInputStream obj = new ObjectInputStream(dados);
            while (dados.available() != 0) {
                Cliente novo = (Cliente) obj.readObject();
                clientes.add(novo);
            }
            obj.close();
            dados = new FileInputStream(arqDadosJogos);
            obj = new ObjectInputStream(dados);
            while (dados.available() != 0) {
                Jogo novo = (Jogo) obj.readObject();
                jogos.add(novo);
            }
            obj.close();
            dados = new FileInputStream(arqDadosCompras);
            obj = new ObjectInputStream(dados);
            while (dados.available() != 0) {
                Compra novo = (Compra) obj.readObject();
                compras.add(novo);
            }
            obj.close();
            listaClientes = clientes;
            listaCompras = compras;
            listaJogos = jogos;
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado.");
            System.out.println("Clientes em branco.");
            System.out.print("Nome do arquivo de dados: ");
            arqDadosCliente = teclado.nextLine();
            pausa(teclado);
        } catch (IOException ex) {
            System.out.println("Problema no uso do arquivo.");
            System.out.println("Favor reiniciar o sistema.");
            pausa(teclado);
        } catch (ClassNotFoundException cex) {
            System.out.println("Classe não encontrada: avise ao suporte.");
            clientes = new ArrayList<Cliente>();
            jogos = new ArrayList<Jogo>();
            compras = new ArrayList<Compra>();
            pausa(teclado);
        }
    }

    static void pausa(Scanner teclado) {
        System.out.println("Enter para continuar.");
        teclado.nextLine();
    }

    public static void Menu() {
        limparTela();

        int opcao = 0;
try{
        do {
            limparTela();
            System.out.println("XULAMBS GAMES");
            System.out.println("==========================");
            System.out.println("1 - CADASTRAR CLIENTE");
            System.out.println("2 - CADASTRAR JOGO");
            System.out.println("3 - CADASTRO COMPRA");
            System.out.println("4 - RELATORIOS");
            System.out.println("5 - AREA CLIENTE");
            System.out.println("0 - SAIR.");
            System.out.print("Escolha: ");
            opcao = Integer.parseInt(teclado.nextLine());
            if (opcao != 0) {
                switch (opcao) {
                    case 1:
                        novoCliente(teclado);

                        break;
                    case 2:
                        novoJogo(teclado);
                        break;
                    case 3:
                        novaCompra(teclado);
                        break;
                    case 4:
                        menuRelatorios(teclado);
                        break;
                    case 5:
                        historicoCliente(teclado);
                        break;
                    default:
                        System.out.println("Opção invalida");
                        break;
                }
            }
        } while (opcao != 0);
    }catch(Exception e){
        System.out.println("Entrada invalida");
        pausa(teclado);
    }
    }

    public static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static void menuRelatorios(Scanner teclado) {
        limparTela();
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
        int opcao = 0;
        int mes, ano;
        System.out.println("Gerencia Xulambs:");
        System.out.println("1-Valor mensal");
        System.out.println("2-Valor medio:");
        System.out.println("3-Mais vendido:");
        System.out.println("4-Menos vendido:");
        opcao = Integer.parseInt(teclado.nextLine());
        switch (opcao) {
            case 1:
                limparTela();
                System.out.println("Digite o mes");
                mes = Integer.parseInt(teclado.nextLine());
                System.out.println("Digite o ano");
                ano = Integer.parseInt(teclado.nextLine());
                System.out.println("Valor mensal: R$"
                        + decimalFormat.format(gerencia.valorMensal(listaCompras, LocalDate.of(ano, mes, 1))));
                pausa(teclado);
                break;
            case 2: 
            limparTela();
                System.out.println("Valor medio: R$" + decimalFormat.format(gerencia.valorMedioCompras(listaCompras)));
                pausa(teclado);
                break;
            case 3:
            limparTela();
                System.out.println("Mais vendidos:" + gerencia.MaisVendidos(listaCompras));
                pausa(teclado);
                break;
            case 4:
            limparTela();
                System.out.println("Menos vendidos:" + gerencia.MenosVendidos(listaCompras));
                pausa(teclado);
            default:
            limparTela();
                System.out.println("Opção invalida");
                pausa(teclado);
                return;
        }
    }

    private static void historicoCliente(Scanner teclado) {
        limparTela();
        String nome;
        int opcao = 0;
        Jogo jogoTipo;
        System.out.print("Digite o nome de usuario do Cliente:");
        nome = teclado.nextLine();
        try {
            Cliente clienteHistorico = listaClientes.stream()
                    .filter(x -> x.getNomeUsuario().toLowerCase().equals(nome.toLowerCase())).findFirst().get();
            if (clienteHistorico != null) {
                limparTela();
                System.out.println("Tipo de jogo:");
                System.out.println("0-SemFiltro:");
                System.out.println("1-Promoção");
                System.out.println("2-Regular:");
                System.out.println("3-Premium:");
                System.out.println("4-Lançamento:");
                opcao = Integer.parseInt(teclado.nextLine());
                switch (opcao) {
                    case 0:
                        jogoTipo = null;
                        break;
                    case 1:
                        jogoTipo = new JogoPromocao();
                        break;
                    case 2:
                        jogoTipo = new JogoRegular();
                        break;
                    case 3:
                        jogoTipo = new JogoPremium();
                        break;
                    case 4:
                        jogoTipo = new JogoLancamento();
                        break;
                    default:
                        System.out.println("Opção invalida");
                        return;
                }
                limparTela();
                System.out.println("filtrar por data?");
                System.out.println("1-Sim");
                System.out.println("2-Não");
                opcao = Integer.parseInt(teclado.nextLine());

                int mes = 0;
                int ano = 0;
                switch (opcao) {
                    case 1:
                        System.out.println("Digite o mes");
                        mes = Integer.parseInt(teclado.nextLine());
                        System.out.println("Digite o ano");
                        ano = Integer.parseInt(teclado.nextLine());
                        limparTela();
                        if (jogoTipo == null) {
                            gerencia.historicoDeComprasTipoData(listaCompras, clienteHistorico, null,
                                    LocalDate.of(ano, mes, 1));
                                    pausa(teclado);
                        } else {
                            gerencia.historicoDeComprasTipoData(listaCompras, clienteHistorico, jogoTipo.getClass(),
                                    LocalDate.of(ano, mes, 1));
                                    pausa(teclado);
                        }
                        break;
                    case 2:
                        if (jogoTipo == null) {
                            gerencia.historicoDeComprasTipoData(listaCompras, clienteHistorico, null, null);
                            pausa(teclado);
                        }
                        gerencia.historicoDeComprasTipoData(listaCompras, clienteHistorico, jogoTipo.getClass(), null);
                        pausa(teclado);
                        break;
                    default:
                        System.out.println("Opção inválida");
                        return;
                }

            } else {
                System.out.println("Cliente não encontrado");
                pausa(teclado);
                return;
            }
        } catch (Exception ex) {
            System.out.println("Cliente não encontrado");
        }

    }

    private static void novoJogo(Scanner teclado) {
        String nome;
        double preco;
        int opcao = 0;
        System.out.print("Digite o nome do jogo:");
        nome = teclado.nextLine();
        System.out.print("Digite o preco do jogo:");
        preco = Double.parseDouble(teclado.nextLine());
        limparTela();
        System.out.print("Digite o tipo de jogo");
        System.out.print("\n1- Regular");
        System.out.print("\n2- Promocao");
        System.out.print("\n3- Premium");
        System.out.print("\n4- Lançamento\n");
        opcao = Integer.parseInt(teclado.nextLine());
        switch (opcao) {
            case 1:
                listaJogos.add(new JogoRegular(nome, preco));
                break;
            case 2:
                listaJogos.add(new JogoPromocao(nome, preco));
                break;
            case 3:
                listaJogos.add(new JogoPremium(nome, preco));
                break;
            case 4:
                listaJogos.add(new JogoLancamento(nome, preco));
            default:
                System.out.print("Digite uma opção valida");
        }
    }

    private static void novaCompra(Scanner teclado) {
        int x = 0;
        int opcao = 0;
        ArrayList<Jogo> jogosCompra = new ArrayList<Jogo>();
        Cliente clienteCompra;
        Compra compraFinal;
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
        decimalFormat.setRoundingMode(RoundingMode.DOWN);
        limparTela();
        System.out.print("\nDigite o numero do jogo");
        System.out.print("\nDigite -1 para parar de adicionar\n");
        for (Jogo jogo : listaJogos) {
            System.out.print("\nindice: " + x + " " + jogo.getNome() + " R$" + jogo.getPreco());
            x++;
        }
        do {
            System.out.print("\nDigite o numero do jogo:");
            opcao = Integer.parseInt(teclado.nextLine());
            if (opcao != -1 && opcao < listaJogos.size()) {
                jogosCompra.add(listaJogos.get(opcao));
            }
        } while (opcao != -1);
        x = 0;
        System.out.print("\nDigite o numero do Cliente:\n");
        for (Cliente cliente : listaClientes) {
            System.out.println(
                    "indice: " + x + " Nome:" + cliente.getNome() + " Nome usuario: " + cliente.getNomeUsuario());
            x++;
        }
        opcao = Integer.parseInt(teclado.nextLine());
        clienteCompra = listaClientes.get(opcao);

        compraFinal = new Compra(jogosCompra, clienteCompra);
        listaCompras.add(compraFinal);
        System.out.println("Compra para o cliente: " + clienteCompra.getNome() + " adicionada");
        System.out.println("Valor final com descontos: R$" + decimalFormat.format(compraFinal.getPrecoFinal()));
        pausa(teclado);

    }

    private static void novoCliente(Scanner teclado) throws InvalidAttributeValueException {
        String nome;
        String nomeUsuario;
        String senha;
        String email;
        int opcao = 0;
        System.out.print("Digite o nome do Cliente:");
        nome = teclado.nextLine();
        System.out.print("Digite o nome de usuario:");
        nomeUsuario = teclado.nextLine();
        System.out.print("Digite a senha do usuario:");
        senha = teclado.nextLine();
        System.out.print("Digite o email:");
        email = teclado.nextLine();
        limparTela();
        System.out.print("Digite o tipo de cliente");
        System.out.print("\n1- Cadastrado");
        System.out.print("\n2- Empolgado");
        System.out.print("\n3- Fanatico\n");

        opcao = Integer.parseInt(teclado.nextLine());
        switch (opcao) {
            case 1:
                listaClientes.add(FabricaCliente.criar("cadastrado", nome, nomeUsuario, senha, email));
                break;
            case 2:
                listaClientes.add(FabricaCliente.criar("empolgado", nome, nomeUsuario, senha, email));
                break;
            case 3:
                listaClientes.add(FabricaCliente.criar("fanatico", nome, nomeUsuario, senha, email));
                break;
            default:
                System.out.print("Digite uma opção valida");
                pausa(teclado);
                break;
        }

    }

    private static void inicializaVariaveis() {
        listaClientes = new ArrayList<Cliente>();
        listaJogos = new ArrayList<Jogo>();
        listaCompras = new ArrayList<Compra>();
        gerencia = new GerenciaXulambs();
    }

    public static void main(String[] args) throws Exception {

        inicializaVariaveis();
        teclado = new Scanner(System.in, "UTF-8");
        carregarDados(teclado);

        Menu();

        gravarDados();
    }

}
