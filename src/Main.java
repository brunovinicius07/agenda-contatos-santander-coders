import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Agenda agenda = new Agenda();
        List<Telefone> telefones = new ArrayList<>();
        Contato contato = new Contato();

        int opcao;
        do {
            System.out.println(""" 
            ##################
            ##### AGENDA #####
            ##################
            """);

            GetAllContatos(agenda);

            System.out.println("""
            >>>> Menu <<<<
            1 - Adicionar Contato
            2 - Remover Contato
            3 - Editar Contato
            4 - Sair
            
            Escolha uma opção:""");

            opcao = sc.nextInt();
            sc.nextLine();
            switch (opcao) {
                case 1:
                    registrarContato(sc, agenda);
                    break;
                case 2:
                    deleteContato(sc, agenda);
                    break;
                case 3:
                    updateContato(sc, agenda, telefones, contato);
                    break;
                case 4:
                    System.out.println("Saindo do sistema!");
                    break;
                default:
                    System.out.println("Opção " + opcao + " é inválida, Escolha 1, 2, 3 ou 4!");
            }
        } while (opcao != 4);

        sc.close();
    }

    private static void GetAllContatos(Agenda agenda) {
        List<Contato> contatos = agenda.getContatos();

        if (contatos.isEmpty()) {
            System.out.println(">>>> Contatos <<<< \n");
            System.out.println("Nenhum contato até o momento! \n");
        } else {
            System.out.println(">>>> Contatos <<<<");
            for (Contato contato : contatos) {
                System.out.println(contato.getId() + " | " + contato.getNome() + " " + contato.getSobreNome());
            }
            System.out.println();
        }
    }

    public static void registrarContato(Scanner sc, Agenda agenda) {
        List<Telefone> telefones = new ArrayList<>();

        System.out.println("Digite o primeiro nome do contato:");
        String nome = sc.nextLine();

        System.out.println("Digite o sobrenome do contato:");
        String sobreNome = sc.nextLine();

        Contato novoContato = new Contato(nome, sobreNome, telefones);

        agenda.addContato(novoContato);

        registrarTelefones(sc, agenda, telefones, novoContato);

    }
    private static void updateContato(Scanner sc, Agenda agenda, List<Telefone> telefones, Contato contato) {
        if (agenda.getAgenda().isEmpty()) {
            System.out.println("Não existe contato!");
            return;
        }

        long id;
        do {
            for (Contato cont : agenda.getContatos()){
                System.out.println(cont.getId() + "| "  + cont.getNome() + " " +cont.getSobreNome());
            }
            System.out.println("Digite o ID do contato a ser editado:");
            id = sc.nextLong();
            contato = agenda.getContatoById(id);

            if (contato == null) {
                System.out.println("Contato com ID " + id + " não existe!");
            }
        } while (contato == null);

        int opcao;
        do {
            System.out.println("""
                            O que você deseja alterar ?
                            1 - Nome
                            2 - Sobrenome
                            3 - Telefone
                            4 - Voltar para o menu principal
                            Digite sua opção:""");
            opcao = sc.nextInt();
            sc.nextLine();


            switch (opcao) {
                case 1:
                    System.out.println("Digite o primeiro nome do contato:");
                    String nome = sc.nextLine();
                    contato.setNome(nome);
                    String sobreNome = contato.getSobreNome();
                    System.out.println("Nome alterado com sucesso! Contato: " + nome + " " + sobreNome);
                    break;
                case 2:
                    System.out.println("Digite o sobrenome do contato:");
                    String nome1 = contato.getNome();
                    String sobreNome1 = sc.nextLine();
                    contato.setSobreNome(sobreNome1);
                    System.out.println("Sobre nome alterado com sucesso! Contato: " + nome1 + " " + sobreNome1);
                    break;
                case 3:
                    updateTelefoneDoContato(sc, agenda, telefones, contato);
                    break;
                case 4:
                    System.out.println("Voltando para o menu principal!");
                    break;
                default:
                    System.out.println("Opção " + opcao + " é inválida, Escolha 1, 2, 3, ou 4!");
                    break;
            }
        }while (opcao != 4);
    }

    private static void deleteContato(Scanner sc, Agenda agenda) {
        if (agenda.getAgenda().isEmpty()) {
            System.out.println("Não existe! contato!");
            return;
        }

        Long id;
        boolean contatoRemovido = false;
        do {
            for (Contato contato : agenda.getContatos()){
                System.out.println(contato.getId() + "| "  + contato.getNome() + " " +contato.getSobreNome());
            }
            System.out.println("Digite o ID do contato que você deseja remover:");
            id = sc.nextLong();

            if (agenda.getContatoById(id) != null) {
                agenda.removerContato(id);
                System.out.println("Contato removido com sucesso! \n");
                contatoRemovido = true;
            } else {
                System.out.println("Contato com ID " + id + " não existe!");
            }

            if (!agenda.getContatos().isEmpty()) {
                String removerOutroContato;
                do {

                    System.out.println("Deseja remover outro contato? (s/n) ");
                    sc.nextLine();

                    removerOutroContato = sc.nextLine();

                    if (!Objects.equals(removerOutroContato, "s") && !Objects.equals(removerOutroContato, "n")) {
                        System.out.println("Opção incorreta, digite 's' para SIM e 'n para NÃO!'");
                    }

                    if (Objects.equals(removerOutroContato, "s")) {
                        contatoRemovido = false;
                    } else {
                        contatoRemovido = true;
                    }
                } while (!Objects.equals(removerOutroContato, "s") && !Objects.equals(removerOutroContato, "n"));
            }
        } while (!contatoRemovido);
    }


    private static void updateTelefoneDoContato(Scanner sc, Agenda agenda, List<Telefone> telefones, Contato contato) {
        int opcao;
        do {
            System.out.println("""
                            O que você deseja alterar ?
                            1 - Adicionar um novo número
                            2 - Editar um número existente
                            3 - Apagar um número existente
                            4 - Voltar para o menu de editar contato
                            Digite sua opção:""");
            opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    registrarTelefones(sc, agenda, telefones, contato);
                    break;
                case 2:
                    updateTelefoneExistente(sc, agenda, contato);
                    break;
                case 3:
                    deletarTelefoneExistente(sc, contato);
                    break;
                case 4:
                    System.out.println("Voltando para o menu de editar contato!");
                    break;
                default:
                    System.out.println("Opção " + opcao + " é inválida, Escolha 1, 2, 3 ou 4!");
                    break;
            }
        } while (opcao != 4);
    }

    private static void updateTelefoneExistente(Scanner sc, Agenda agenda, Contato contato) {
        List<Telefone> telefonesDoContato = contato.getTelefones();

        if (telefonesDoContato.isEmpty()) {
            System.out.println("Não há telefones para editar.");
            return;
        }

        Long idTelefone;
        List<Long> idsTelefoneDoContato;
        Telefone telefone;
        boolean adicionarOutro = true;
        do {
            for (Telefone tel : contato.getTelefones()) {
                System.out.println(tel.getId() + "| (" + tel.getDdd() + ") " + tel.getNumero());
            }

            System.out.println("Escolha o id do telefone de " + contato.getNome() + " " + contato.getSobreNome() + " para editar:");
            idTelefone = sc.nextLong();
            telefone = agenda.getTelefoneById(idTelefone);

            idsTelefoneDoContato = contato.getTelefones().stream().map(Telefone::getId).toList();
            if (!idsTelefoneDoContato.contains(idTelefone)) {
                System.out.println("ID inválido.");
            }

        } while (!idsTelefoneDoContato.contains(idTelefone));

        do {
            System.out.println("Digite o novo DDD:");
            Long novoDdd = sc.nextLong();

            System.out.println("Digite o novo número de telefone:");
            Long novoNumero = sc.nextLong();
            sc.nextLine();

            Boolean existeTelefone = false;

            for (Contato contato1 : agenda.getContatos())
                for (Telefone telefone1 : contato1.getTelefones()) {
                    if (telefone1.getDdd().equals(novoDdd) && telefone1.getNumero().equals(novoNumero)) {
                        existeTelefone = true;
                        System.out.println("O telefone " + "(" + novoDdd + ")" + " " + novoNumero + " já existe!");
                    }
                }

            if (!existeTelefone) {
                telefone.setDdd(novoDdd);
                telefone.setNumero(novoNumero);
                System.out.println("Telefone atualizado com sucesso!\n");
                System.out.println("Numero novo: ID " + telefone.getId() + " DDD2 " + telefone.getDdd() + " NUM2 " + telefone.getNumero());

            }

            String resposta;
            do {
                System.out.println("Deseja editar o número? (s/n)");
                resposta = sc.nextLine();

                if (!Objects.equals(resposta, "s") && !Objects.equals(resposta, "n")) {
                    System.out.println("Opção incorreta, digite 's' para SIM e 'n para NÃO!'");
                }
            }while (!Objects.equals(resposta, "s") && !Objects.equals(resposta, "n"));

            if (resposta.equalsIgnoreCase("n")) {
                adicionarOutro = false;
            }

        }while (adicionarOutro);
    }

    private static void deletarTelefoneExistente(Scanner sc, Contato contato) {
        List<Telefone> telefones = contato.getTelefones();

        if (telefones.isEmpty()) {
            System.out.println("Não há telefones para apagar.");
            return;
        }

        contato.getTelefonesDoContato();
        int indice;
        do {
            System.out.println("Escolha o id do telefone para apagar:");
            indice = sc.nextInt() - 1;
            sc.nextLine();
            if (indice < 0 || indice >= telefones.size()) {
                System.out.println("Índice inválido.");
            }
        } while (indice < 0 || indice >= telefones.size());

        telefones.remove(indice);
        System.out.println("Telefone removido com sucesso.");
    }

    public static List<Telefone> registrarTelefones(Scanner sc, Agenda agenda, List<Telefone> telefones, Contato contato) {
        boolean adicionarOutro = true;

        do {
            System.out.println("Digite o DDD do telefone:");
            Long ddd = sc.nextLong();
            sc.nextLine();

            System.out.println("Digite o número do telefone:");
            Long numero = sc.nextLong();
            sc.nextLine();

            Boolean existeTelefone = false;
            for (Contato contato1 : agenda.getContatos())
                for (Telefone telefone : contato1.getTelefones()) {
                    if (telefone.getDdd().equals(ddd) && telefone.getNumero().equals(numero)) {
                        existeTelefone = true;
                        System.out.println("O telefone " + "(" + ddd + ")" + " " + numero + " já existe!");
                    }
                }

            if (!existeTelefone){
                Telefone novoTelefone = new Telefone(ddd, numero);
                contato.addTelefone(novoTelefone);
                System.out.println("Telefone adicionado com sucesso!\n");
            }

            String resposta;
            do {
                System.out.println("Deseja adicionar outro número? (s/n)");
                resposta = sc.nextLine();

                if (!Objects.equals(resposta, "s") && !Objects.equals(resposta, "n")) {
                    System.out.println("Opção incorreta, digite 's' para SIM e 'n para NÃO!'");
                }
            }while (!Objects.equals(resposta, "s") && !Objects.equals(resposta, "n"));

            if (resposta.equalsIgnoreCase("n")) {
                adicionarOutro = false;
            }
        } while (adicionarOutro);
        return telefones;
    }
}