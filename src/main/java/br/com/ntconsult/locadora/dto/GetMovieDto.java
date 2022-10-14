package br.com.ntconsult.locadora.dto;

public class GetMovieDto {

    private String name;

    public GetMovieDto() {
    }

    public GetMovieDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
