package com.example.webpet.response;

import com.example.webpet.entity.Pet;

import java.util.List;

public class PetResponse {
    private Pet pet;
    private String message;
    private int success;
    private List<Pet>pets;

    public PetResponse(Pet pet, String message, int success){
        this.message=message;
        this.success=success;
        this.pet=pet;
    }
    public PetResponse(List<Pet> pets, String message, int success){
        this.message=message;
        this.success=success;
        this.pets=pets;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

    public int getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public Pet getPet() {
        return pet;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }
}
