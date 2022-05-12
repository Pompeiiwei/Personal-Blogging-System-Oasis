-- TODO: Add your database init script here. This should initialize all your tables, and add any initial data required.

SET foreign_key_checks = 0;
DROP TABLE IF EXISTS comments;
DROP TABLE IF EXISTS articles;
DROP TABLE IF EXISTS user;

CREATE TABLE user (

                                 userId     BIGINT AUTO_INCREMENT NOT NULL UNIQUE,
                                 userName   VARCHAR(99) UNIQUE,
                                 userPassword  VARCHAR(1000),
                                 salt       VARCHAR(1000),
                                 iterations BIGINT,
                                 firstName  VARCHAR(99),
                                 lastName   VARCHAR(99),
                                 email      VARCHAR(99),
                                 birthDate  DATE,
                                 gender     VARCHAR(99),
                                 avatar  VARCHAR(256),
                                 description VARCHAR(1000),
                                 PRIMARY KEY (userId)
);

INSERT INTO user (userId, userName, userPassword, salt, iterations, firstName, lastName, email, birthDate, gender, avatar, description) values (17,'James','lQJnHudjoYUkIOsIlTM/VwZbk2BzLOwKc+NSiVpbm+6IbRGTYg2MUek+0fVtZFpbL9ir+OlMJ+ZKCHGofjAVgA==','ZoC/Ekl/Sj0Z6jEoW2e6k5LckB3oDpAz3HNX0S0zAWM=',100195,'Lebron','James','LebronJames@NBA.COM','1984-12-30','Male','DefaultAvatar/04.png','KING!!!!');





# # tested user data
# INSERT INTO user(userId, userName)VALUES
# (1, 'Ying'),
# (2, 'Elsa'),
# (3, 'Aria'),
# (4, 'Emily');


INSERT INTO user(userId, userName,avatar)VALUES
(1, 'Ying','DefaultAvatar/04.png'),
(2, 'Elsa','DefaultAvatar/03.png'),
(3, 'Aria','DefaultAvatar/02.png'),
(4, 'Emily','DefaultAvatar/01.png');


CREATE TABLE articles(
                         artId INT NOT NULL AUTO_INCREMENT,
                         userId BIGINT,
                         userName VARCHAR(99),
                         title VARCHAR(50),
                         date VARCHAR(30),
                         body TEXT,
                         PRIMARY KEY (artId),
                         FOREIGN KEY (userId) REFERENCES user(userId),
                         FOREIGN KEY (userName) REFERENCES user(userName)

);

INSERT INTO articles(userId,userName, title, date, body) VALUES
(1, 'Ying', 'Apirana Turupa Ngata', '2020-01-12 23:13:45','One of 15 children, Ngata was born in Te Araroa (then called Kawakawa), a small coastal town about 175 kilometres north of Gisborne, New Zealand.[1] His iwi was Ngāti Porou. His father was Paratene Ngata, a tribal leader and expert in traditional lore, and his mother was Katerina Naki, the daughter of an itinerant Scot, Abel Knox.[2] Ngata was greatly influenced both by his father and by his great-uncle Ropata Wahawaha (who had led loyal kupapa Ngāti Porou forces against their Pai Mārire enemy (commonly known as Hauhau) in the East Cape War and later Te Kooti''s escapees from the Chatham Islands.)[3] Ngata was raised in a Māori environment, speaking the Māori language, but his father also ensured that Ngata learned about the Pākehā world, believing that this understanding would be of benefit to Ngāti Porou.'),
(3, 'Aria', 'Edmund Hillary', '2020-01-20 23:13:45','Sir Edmund Percival Hillary KG ONZ KBE (20 July 1919 – 11 January 2008) was a New Zealand mountaineer, explorer and philanthropist. On 29 May 1953, Hillary and Nepalese Sherpa mountaineer Tenzing Norgay became the first climbers to reach the summit of Mount Everest. They were part of the ninth British expedition to Everest, led by John Hunt. Hillary was named by Time as one of the 100 most influential people of the 20th century. Hillary became interested in mountaineering while in secondary school, making his first major climb in 1939, reaching the summit of Mount Ollivier. He served in the Royal New Zealand Air Force as a navigator during World War II. Prior to the 1953 Everest expedition, Hillary had been part of the British reconnaissance expedition to the mountain in 1951, as well as an unsuccessful attempt to climb Cho Oyu in 1952. As part of the Commonwealth Trans-Antarctic Expedition he reached the South Pole overland in 1958. He subsequently reached the North Pole, making him the first person to reach both poles and summit Everest.\nFollowing his ascent of Everest, Hillary devoted most of his life to helping the Sherpa people of Nepal through the Himalayan Trust, which he founded. Through his efforts, many schools and hospitals were built in Nepal.'),
(1, 'Ying', 'Ernest Rutherford', '2020-01-23 23:13:45', 'Ernest Rutherford was the son of James Rutherford, a farmer, and his wife Martha Thompson, originally from Hornchurch, Essex, England.[10] James had emigrated to New Zealand from Perth, Scotland, "to raise a little flax and a lot of children". Ernest was born at Brightwater, near Nelson, New Zealand. His first name was mistakenly spelled ''Earnest'' when his birth was registered.[11]'),
(4, 'Emily', 'Kate Sheppard', '2020-02-13 23:13:45', 'Kate Sheppard was born Catherine Wilson Malcolm in Liverpool, England to Scottish parents Jemima Crawford Souter and Andrew Wilson Malcolm.[4] She generally preferred to spell her given name "Katherine", or abbreviate it to "Kate". She received a good education, and was noted for her intellectual ability and broad knowledge. For a time she lived with her uncle, a minister of the Free Church of Scotland at Nairn.[3] In 1869, several years after her father''s death, Sheppard and her siblings immigrated with their mother to Christchurch, New Zealand. She married Walter Allen Sheppard three years later, and their only child, Douglas, was born on 8 December 1880.[4]'),
(2, 'Elsa', 'Frozen', '2020-03-12 23:13:45', 'When the newly crowned Queen Elsa accidentally uses her power to turn things into ice to curse her home in infinite winter, her sister Anna teams up with a mountain man, his playful reindeer, and a snowman to change the weather condition.'),
(3, 'Aria', 'Breaking bad', '2020-06-10 23:13:45', 'A high school chemistry teacher diagnosed with inoperable lung cancer turns to manufacturing and selling methamphetamine in order to secure his family''s future.'),
(1, 'Ying', 'Identity','2020-06-15 16:44:43','When a vicious storm breaks out in the Nevada desert, 10 people seek refuge in an isolated motel. At the same time, a serial killer (Pruitt Taylor Vince) under the care of psychiatrist Doctor Mallick (Alfred Molina) -- who has just found the killer''s revealing journal -- awaits execution for murdering a group of motel guests. When the storm-stranded travelers realize they are being killed off one by one, limo driver Ed Dakota (John Cusack) bids to stay alive and reveal the murderer''s identity.');





CREATE TABLE comments(
                             comId INT(8) AUTO_INCREMENT,
                             userId BIGINT,
                             userName   VARCHAR(99),
                             artId INT(8) NOT NULL,
                             comDate VARCHAR(32) NOT NULL,
                             comContent TEXT DEFAULT NULL,
                             parentID INT(8) DEFAULT NULL,
                             hidden BOOLEAN DEFAULT FALSE,
                             PRIMARY KEY (comId),
                             FOREIGN KEY (userId) REFERENCES user (userId),
                             FOREIGN KEY (userName) REFERENCES user (userName),
                             FOREIGN KEY (artId) REFERENCES  articles (artId)
);

INSERT INTO comments(userId,userName,artId,comContent,comDate) VALUES
(1,'Ying',1,'This is a comment for first article.', '2020-06-09 13:45'),
(2,'Elsa',2,'This is another comment for first article.', '2020-06-09 13:46'),
(3,'Aria',3,'This is a comment for second article.', '2020-06-09 14:46'),
(3,'Aria',4,'This is another comment for second article.', '2020-06-09 15:46'),
(4,'Emily',5,'This is a comment for third article.', '2020-06-09 13:46'),
(4,'Emily',6,'This is another comment for third article.', '2020-06-09 15:46'),
(3,'Aria',6,'This is a comment for forth article.', '2020-06-09 12:46'),
(2,'Elsa',4,'This is another comment for forth article.', '2020-06-09 13:46'),
(3,'Aria',3,'This is last comment for first article.', '2020-06-09 14:46');

