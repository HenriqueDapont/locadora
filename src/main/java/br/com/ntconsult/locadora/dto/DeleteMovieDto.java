package br.com.ntconsult.locadora.dto;

public class DeleteMovieDto {

    private String name;

    public DeleteMovieDto() {
    }

    public DeleteMovieDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
