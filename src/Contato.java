import java.util.List;
import java.util.Objects;

public class Contato {

    private static long ultimoId = 0;

    private Long id;
    private String nome;
    private String sobreNome;
    private List<Telefone> telefones;

    public Contato() {
    }

    public Contato(String nome, String sobreNome, List<Telefone> telefones) {
        this.id = gerarNovoId();
        this.nome = nome;
        this.sobreNome = sobreNome;
        this.telefones = telefones;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobreNome() {
        return sobreNome;
    }

    public void setSobreNome(String sobreNome) {
        this.sobreNome = sobreNome;
    }

    public List<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<Telefone> telefones) {
        this.telefones = telefones;
    }

    public void addTelefone(Telefone telefone) {
        telefones.add(telefone);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contato contato = (Contato) o;
        return Objects.equals(id, contato.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public void getTelefonesDoContato() {
        for (Telefone telefone : telefones) {
            System.out.println(telefone.getId() + ": (" + telefone.getDdd() + ") " + telefone.getNumero());
        }
    }

    public static synchronized long gerarNovoId() {
        return ++ultimoId;
    }
}
