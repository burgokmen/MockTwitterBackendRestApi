# Mock Twitter Project

This is the final project for my 6-month web developer training program, and it is a mock Twitter web application.

## Project Description

This project is a simplified version of Twitter, where users can create accounts, post tweets, follow other users, and view their home timeline. The main goal of this project is to showcase the skills and knowledge I have acquired during my web development training.

## Features

- User Registration and Authentication
- Tweet Creation and Posting
- Following and Followers System
- Home Timeline
- User Profile Pages

#### Endpoints & Requests:

Below are request you can make via Rest Api.

## Technologies Used

Java 17
Maven 
Spring Boot
Postgres Sql


| Request | URL                       | Definition|
| ------- | ------------------------- | --------- |
| POST    | `${URL}/profile/register` | Register  |
| POST    | `${URL}/profile/logout`   | Logout    |
| POST    | `${URL}/profile/login`    | Login     |

**Tweet İşlemleri:**

| Request  | URL                       | Definition            |
| -------  | ------------------------- | --------------------- |
| GET      | `${URL}/tweet/`           | getAllTweets          |
| GET      | `${URL}/tweet/id`         | getAllTweetsById      |
| POST     | `${URL}/tweet`            | Post a Tweet          |
| PUT      | `${URL}/tweet/id`         | Edit a Tweet          |
| DELETE   | `${URL}/tweet/id`         | Delete a Tweet        |
| POST     | `${URL}/tweet/like/id`    | Like                  |
| DELETE   | `${URL}/tweet/like/id`    | Unlike                |
| POST     | `${URL}/tweet/retweet/id` | Retweet               |
| POST     | `${URL}/tweet/reply/id`   | Make a Comment        |
| DELETE   | `${URL}/tweet/reply/id`   | Delete a Comment      |


## Usage

- Create an account or log in if you already have one.
- Create tweets, follow other users, and explore the timeline.
- Visit user profiles to see their tweets and followers.

## Demo

You can view a live demo of this project at .

## Contact

If you have any questions or feedback, please feel free to contact me at brunogokmen@gmail.com 

Thank you for checking out my project!

