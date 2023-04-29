CREATE DATABASE chatbot3;
use chatbot3;
CREATE TABLE responses (
    id INT NOT NULL AUTO_INCREMENT,
    question VARCHAR(255) NOT NULL,
    response VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE users (
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
    
);


CREATE TABLE unanswered_questions (
    users VARCHAR(255) NOT NULL,
    question VARCHAR(255) NOT NULL
    
);
ALTER TABLE users
DROP COLUMN email;

INSERT INTO users (name, password)
VALUES
('yuva','1234');

DELETE FROM users
WHERE name = yuva;

SELECT * FROM users;
ALTER TABLE users CHANGE name username VARCHAR(255) NOT NULL;




INSERT INTO responses (question, response)
VALUES
('hi%', 'Hello! How can I assist you today?'),
('hey%', 'Hi there! How may I help you?'),
('hello%', 'Hi! What can I help you with today?'),
('how are you%', 'I am doing well, thank you! How can I assist you?'),
('what is your name%', 'My name is barney. How can I assist you?'),
('what do you do%', 'I am here to assist you with any questions or problems you may have.'),
('can you help me%', 'Of course! Please let me know how I can assist you.'),
('thank you%', 'You\'re welcome! Is there anything else I can help you with?'),
('bye%', 'Goodbye! Have a great day.'),
('see you%', 'See you later!'),
('take care%', 'You too, take care!'),
('how can I contact you%', 'You can contact me through this chat'),
('what is the weather today%', 'I am sorry, I do not have the ability to check the weather. Please try using a search engine or weather app.'),
('what is your purpose%', 'My purpose is to assist you with any questions or problems you may have.'),
('what is your favorite color%', 'I am a chatbot and do not have the ability to perceive colors.'),
('what do you think about artificial intelligence%', 'As an AI chatbot, I believe that artificial intelligence has the potential to revolutionize the way we live and work.'),
('tell me a joke%', 'Why don\'t scientists trust atoms? Because they make up everything!'),
('what is the meaning of life%', 'The meaning of life is subjective and varies from person to person.'),
('what is your favorite food%', 'I am a chatbot and do not have the ability to eat or taste food.'),
('what is your favorite movie%', 'I am a chatbot and do not have the ability to watch movies.'),
('what is your favorite book%', 'I am a chatbot and do not have the ability to read books.'),
('what is your favorite song%', 'I am a chatbot and do not have the ability to listen to music.'),
('Can you tell me a fun fact%', 'Sure! Did you know that there is a species of jellyfish that is immortal? It is called the Turritopsis dohrnii, and it can regenerate its cells indefinitely.'),
('What is the meaning of life%', ' That is a philosophical question with many different interpretations. Some people believe that the meaning of life is to pursue happiness, while others believe that it is to fulfill a higher purpose or spiritual destiny. What do you think the meaning of life is?.'),
('who created you?%', 'I can tell you the name of the person who is credited with inventing a specific item or technology if you provide me with the name.'),
('Can you play a game with me?%', 'Iam sorry, Iam not able to play games in the traditional sense, but I can provide you with trivia questions, riddles, or other interactive challenges'),
('How old are you?%', 'I dont have an age in the traditional sense, as Iam a machine learning model created by java technology.'),
('What is your favorite food?%', 'As an AI language model, I dont have personal preferences, but I can recommend popular foods based on user ratings and reviews.');


INSERT INTO responses (question, response) VALUES ('data', LEFT('data', 9999));

ALTER TABLE responses MODIFY response VARCHAR(5000);


INSERT INTO responses (question, response)
VALUES
('Can you tell me a fun fact%', 'Sure! Did you know that there is a species of jellyfish that is immortal? It is called the Turritopsis dohrnii, and it can regenerate its cells indefinitely.'),
('What is the meaning of life%', ' That is a philosophical question with many different interpretations. Some people believe that the meaning of life is to pursue happiness, while others believe that it is to fulfill a higher purpose or spiritual destiny. What do you think the meaning of life is?.'),
('who created you?%', 'I can tell you the name of the person who is credited with inventing a specific item or technology if you provide me with the name.'),
('Can you play a game with me%', 'Iam sorry, Iam not able to play games in the traditional sense, but I can provide you with trivia questions, riddles, or other interactive challenges'),
('How old are you?%', 'I dont have an age in the traditional sense, as Iam a machine learning model created by java technology.'),
('What is your favorite food%', 'As an AI language model, I dont have personal preferences, but I can recommend popular foods based on user ratings and reviews.'),
('Can you tell me a fun fact%', 'Sure! Did you know that sloths only defecate once a week? They climb down from their trees to go to the bathroom, and this is the only time they are vulnerable to predators.'),
('What is the best restaurant nearby%', ' I can provide recommendations for popular restaurants in most cities around the world, based on user ratings and reviews. What city are you interested in?'),
('What is the current news%', 'I can provide you with the latest news updates on any topic or subject that you are interested in. What specific news topic would you like me to search for?'),
('Can you recommend a good book to read%', ' I can recommend popular books based on user ratings and reviews, as well as your personal reading preferences. What type of book are you in the mood for, and what are some books you have enjoyed in the past?'),
('Can you understand me%', 'Yes, I can understand and respond to most natural language questions and commands.'),
('How do I contact customer service?%', ' I can provide customer service contact information for many different companies and organizations. Which company or organization are you trying to reach?'),
('Can you give me some travel tips for [destination]?%', 'I can provide travel recommendations and tips for most popular travel destinations. Where are you planning to travel to?'),
('Can you remind me to task at time%', 'I can set reminders for tasks at specified times. What task would you like me to remind you of, and at what time?'),
('What is the meaning of the acronym%', ' I can provide definitions and explanations of most common acronyms and abbreviations. What acronym do you need me to explain?'),
('Can you tell me a fun fact?%', 'Sure! Here is one: The shortest war in history was between Britain and Zanzibar in 1896, and lasted only 38 minutes.'),
('Can you play a game%', 'Sure, I can play a variety of games with you, such as trivia, word games, and more. What type of game would you like to play?'),
('Can you recommend a restaurant nearby%', 'I can provide restaurant recommendations and reviews for many cities around the world. Which city are you looking for a restaurant in, and what type of cuisine are you interested in?'),
('Can you suggest a movie to watch%', 'Sure, I can suggest movies based on different genres, actors, and directors. What type of movie are you in the mood for?'),
('Can you help me plan my trip to destination%', 'Sure, I can provide travel recommendations and tips for many different destinations around the world. Where are you planning to go, and what do you want to do there?'),
('Can you recommend a workout routine%', 'Sure, I can recommend workout routines based on different goals and fitness levels. What are your fitness goals?'),
('How do I change my password%', 'I can provide instructions and guidance for changing passwords for many different websites and services. What website or service do you need to change your password for?'),
('Can you recommend a book to read%', 'Sure, I can recommend books based on different genres and authors. What type of book are you in the mood for?'),
('How do I create a website%', ' I can provide guidance and resources for creating a website, including website builders and coding languages. What type of website are you interested in creating?'),
('Can you help me troubleshoot my computer or technology issue%', 'I can provide troubleshooting tips and solutions for many different types of technology issues, from software to hardware problems. What type of issue are you experiencing?'),
(' Can you tell me the weather forecast%', ' I can provide current weather conditions and forecasts for many different locations around the world. Where are you located or interested in checking the weather for?'),
(' Can you help me find a product%', ' Sure, what are you looking for specifically?'),
('How can I contact customer support%', 'You can reach our customer support team by phone, email, or through our website is chat function. Which one would you prefer?'),
('Can you recommend a good book%', 'Absolutely! What type of book are you in the mood for? Fiction, non-fiction, mystery, romance, etc.'),
('Can you provide me with some news headlines?%', 'Sure thing! What type of news are you interested in? Sports, politics, entertainment, etc.'),
('Can you help me reset my password%', 'Yes, I can help with that. What is your username or email associated with your account?'),
('Can you give me directions to washington%', 'Sure, what is your current location?'),
('What services do you offer%', 'We offer a variety of services, including (list of services). Would you like more information about any of these services?'),
('Can you provide me with a quote%', 'Absolutely! What type of product or service are you interested in, and what is your budget?'),
('Can you help me place an order%', 'Of course! What product would you like to order, and what is your shipping address?'),
('Can you help me with a technical issue%', 'Absolutely! What type of issue are you experiencing, and can you provide any error messages or codes that you are seeing?'),
('How can I cancel my subscription%', 'Iam sorry to hear that you want to cancel your subscription. You can cancel by logging into your account on our website and going to the subscriptions section. Let me know if you need any further assistance.'),
('Do you offer any discounts or promotions?%', ' Yes, we often have promotions and discounts available. Would you like me to provide more information on any current promotions?'),
('Hey there, how is it going%', 'Hello! Iam just a chat bot, so I dont have feelings, but Iam here to assist you with whatever you need.'),
('Whatis your favorite movie%', 'As a chat bot, I dont have personal preferences, but some of our users have mentioned that they enjoy hollywood movies'),
('Do you have any hobbies%', 'I dont have hobbies since Im a chat bot, but I enjoy helping people with their questions and tasks.'),
('What is your favorite color%', 'As a chat bot, I dont have a favorite color, but what is your favorite color?'),
('Can you recommend a good book%', 'Absolutely! What type of book are you interested in? Fiction, non-fiction, mystery, romance, etc.'),
('What is your favorite type of music%', 'As a chat bot, I dont have personal preferences, but what is your favorite type of music?'),
('How are you doing today%', 'As a chat bot, I dont have emotions, but Iam always here to assist you with whatever you need'),
('What is the meaning of life?%', 'That is a deep philosophical question that has puzzled humanity for centuries. Unfortunately, I dont have a definitive answer, but there are many different perspectives on this topic.'),
('Can you tell me a fun fact?%', 'Sure! Did you know that cats can make over 100 different vocal sounds, while dogs can only make about 10?'),
('Can you tell me about yourself?%', ' Sure! Iam a chat bot designed to assist you with various tasks and answer your questions. Iam always here to help!');

SELECT * FROM responses;
SELECT * FROM users;
