package com.mindlift.api.dto;

public class LoginResponse {
    private boolean success;
    private String rol;
    private String username;
    private Long pacienteId;
    private Long profesionalId;
    private String message;

    public LoginResponse() {}

    public LoginResponse(boolean success, String rol, String username, Long pacienteId, Long profesionalId, String message) {
        this.success = success;
        this.rol = rol;
        this.username = username;
        this.pacienteId = pacienteId;
        this.profesionalId = profesionalId;
        this.message = message;
    }

    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public Long getPacienteId() { return pacienteId; }
    public void setPacienteId(Long pacienteId) { this.pacienteId = pacienteId; }

    public Long getProfesionalId() { return profesionalId; }
    public void setProfesionalId(Long profesionalId) { this.profesionalId = profesionalId; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
