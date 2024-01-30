import java.util.ArrayList;
import java.util.List;

public class Agenda {

    private final List<Contato> contatos;

    public Agenda() {
        this.contatos = new ArrayList<>();
    }

    public List<Contato> getAgenda() {
        return contatos;
    }

    public List<Contato> getContatos() {
        return contatos;
    }

    public void addContato(Contato novoContato) {
        contatos.add(novoContato);
    }

    public Contato getContatoById(Long id) {
        for (Contato contato : this.contatos) {
            if (contato.getId().equals(id)) {
                return contato;
            }
        }
        return null;
    }

    public Telefone getTelefoneById(Long id) {
        for (Contato contato : contatos) {
            for (Telefone telefone : contato.getTelefones()) {
                if (telefone.getId().equals(id)) {
                    return telefone;
                }
            }
        }
        return null;
    }

    public void removerContato(Long id) {
           Contato contato = getContatoById(id);

            if (contato != null) {
                contatos.remove(contato);
            }
    }


}
