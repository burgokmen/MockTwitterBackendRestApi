package com.brutech.mocktwitterbackendrestapi.dto;

import java.util.Date;

public record ProfileResponse(String userHandle, String firstName, String lastName,
                              String email ,String password, String cellular,
                              Date birthday, Date createdAt, String location, String profilePicture ) {
}
