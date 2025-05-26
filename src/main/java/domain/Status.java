package domain;

public enum Status {
    criado,
    em_execucao,
    finalizado;

    public String toString() {
        switch (this) {
            case criado: return "criado";
            case em_execucao: return "em_execucao";
            case finalizado: return "finalizado";
            default: throw new IllegalArgumentException();
        }
    }

    public static Status fromString(String status) {
        switch (status.toLowerCase()) {
            case "criado": return criado;
            case "em_execucao": return em_execucao;
            case "finalizado": return finalizado;
            default: throw new IllegalArgumentException("Status desconhecido: " + status);
        }
    }
}
