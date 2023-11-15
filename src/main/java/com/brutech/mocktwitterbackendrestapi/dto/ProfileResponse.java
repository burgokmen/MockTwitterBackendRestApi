package com.brutech.mocktwitterbackendrestapi.dto;


public record ProfileResponse(Long id, String userHandle, String firstName, String lastName,
                              String email ,String password, String cellular,
                              String birthday, String createdAt,
                              String location, String profilePicture ) {
}
