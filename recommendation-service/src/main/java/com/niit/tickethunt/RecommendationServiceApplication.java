package com.niit.tickethunt;

import com.niit.tickethunt.domain.Event;
import com.niit.tickethunt.repository.EventRepository;
//import com.niit.tickethunt.service.EventService;
//import com.niit.tickethunt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
//import org.springframework.context.annotation.Bean;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.PostConstruct;
//import java.io.File;
//import java.nio.file.Files;
import java.util.ArrayList;
//import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
@EnableSwagger2
@EnableNeo4jRepositories
@EnableTransactionManagement
@EnableEurekaClient
public class RecommendationServiceApplication {

    @Autowired
    private EventRepository eventRepository;

    @PostConstruct
    public void events() {

        List<Event> events = new ArrayList<>();
        try {
            events = Stream.of(
                    new Event(Long.parseLong("1"), "Sita-Ramam", "05-03-2023", "12:00 PM", "Pacific Mall", "PVR", "50", 200, "movie", "Sita Ramam is a 2022 Indian Telugu-language period romance film written and directed by Hanu Raghavapudi. Produced by Vyjayanthi Movies and Swapna Cinema, the film stars Dulquer Salmaan and Mrunal Thakur (in her Telugu debut) in lead roles with Rashmika Mandanna in a major role and Sumanth in the supporting role.", "8.6/10"),
                    new Event(Long.parseLong("3"), "Jersey", "22-04-2023", "10:00 PM", "Mall of India", "INOX", "50", 300, "movie", "In 1986 in Hyderabad, Arjun is an immensely talented Ranji (First-class cricketer) player who is in love with Sarah. Arjun quits cricket when he repeatedly gets rejected to play in the Indian team, due to selection politics. He is given a government job under sports quota, but loses it when he is accused of bribery", "7.3/10"),
                    new Event(Long.parseLong("4"), "Bhool Bhulaiyaa 2", "20-04-2023", "04:00 PM", "NSP", "Wave", "50", 250, "movie", "Across a mansion in Bhawanigarh, Rajasthan in 2004, the priests confine a malevolent spirit named Manjulika, who is hell-bent on attacking the family's daughter-in-law Anjulika to a room. After this, the family deserts the mansion for safety.After an accident, Ruhan agrees to accompany Reet back home. However, due to a series of untimely incidents, he gets caught up in her family's skirmish.", "5.7/10"),
                    new Event(Long.parseLong("5"), "Drishyam 2", "18-02-2023", "02:30 PM", "Promenade", "Cinepolis", "50", 350, "movie", "Drishyam 2 is a 2022 Indian Hindi-language crime thriller film directed by Abhishek Pathak, adapted from the 2021 Malayalam film of the same name, and a sequel to the 2015 film Drishyam. Produced by Pathak and his father Kumar Mangat Pathak under the Panorama Studios banner alongside Viacom18 Studios, with Bhushan Kumar and Krishan Kumar joining as producers under the T-Series Films banner.", "8.4/10"),
                    new Event(Long.parseLong("5"), "Phone Boot", "04-03-2023", "08:00 AM", "Select City", "PVR", "50", 450, "movie", "Major and Gullu two friends who are obsessed with horror since childhood want to make their careers on something related to it but whatever business they have started always failed.During one Halloween party they come across an unknown beautiful women and cant get over her she traces them to their house and discloses that she is a ghost and they can interact with her normally as they have been god gifted with some powers.", "5.7/10"),
                    new Event(Long.parseLong("8"), "Khuda Haafiz 2", "07-02-2023", "02:00 PM", "Elante", "INOX", "50", 400, "movie", "After busting down the flesh traders in Noman and saving his wife Nargis, Sameer returns to India with Nargis, who battles depression and PTSD after the Noman incident. One year later, where the couple try to find their new normal but are seen to be struggling, despite therapy and medicines.Sameer's best friend Deepak loses his brother and sister-in-law to a car accident and they are survived by their 5-year old daughter, Nandini. However, due to his sales job", "7.5/10"),
                    new Event(Long.parseLong("9"), "Jug Jugg Jeeyo", "24-05-2023", "06:00 PM", "Vegas Mall", "Wave", "50", 280, "movie", "Kuldeep Kukoo Saini and Nainaa Sharma marry each other, having loved each other ever since they had gone to school together. The couple relocate to Toronto, Canada for Nainaa's job in a corporate company while Kukoo is employed as a bouncer in a nightclub. Within five years, the couple's marital life gets strained due to prolonged silences, unfinished conversations and resentful hearts while Nainaa is offered a new position as the vice-president of HR at New York City and Kukoo is frustrated with his job.", "6.1/10"),
                    new Event(Long.parseLong("10"), "Cosmo-Food Carnival", "13-02-2023", "09:00 PM", "JLN Stadium", "Paytm Insider", "50", 150, "event", "A food festival is a festival, that uses food, often produce, as its central theme. These festivals have always been a means of uniting communities through celebrations of harvests and giving thanks for a plentiful growing season.Food Festivals throughout the world are often based on traditional farming techniques, seasons Food festivals are related to food culture of an area, whether through the preparation of food served or the time period in which the festival is celebrated.", "9.3/10"),
                    new Event(Long.parseLong("20"), "Joy Town", "03-05-2023", "10:00 AM", "Aerocity", "Star Entertainment", "50", 220, "event", "Come on down to a one-of-a-kind festival that's going to be an edge-of-your-seat joyride and the talk of the town. Get insider access to the thrilling world of BMW motoring as you get your drift mode on in a BMW, witness insane stunts by BMW Motorrad riders, and experience that go-kart feeling in a MINI. Groove to the beats and tunes of your favourite artists.Indulge in culinary delights and wash it all down with a beer at our exclusive beer garden. Or if you fancy a cocktail, our mixologists will be keeping the mood high and the vibe heady well into the evening.", "7/10"),
                    new Event(Long.parseLong("18"), "Arijit Singh Concert", "13-05-2023", "10:00 PM", "Leisure Valley", "Paytm Insider", "50", 340, "event", "Arijit Singh is an Indian musician, singer, composer, music producer, recordist and a music programmer. At the age of 3, he started training under Hazari brothers, and at the age of 9, he got a scholarship from the government for training in vocals in Indian classical music. He predominantly sings in Hindi and Bengali, but has also lent his voice to various other Indian languages. Singh is regarded as one of the most versatile and successful singers in the History of Indian Music and Hindi Cinema.", "8.8/10"),
                    new Event(Long.parseLong("18"), "Miss Supermodel Globe", "10-03-2023", "12:30 PM", "Sirifort Auditorium", "Maybelline", "50", 190, "event", "Miss SuperModel Globe is an International Beauty Pageant, A Influential platform for Young Women to make a Mark in the Global World of Modelling With a Fasion to “Educate MEN, Save & Empower Women.Miss SuperModel Globe is not just a Beauty Pageant, participating in the Pageant will give you International Exposure and Experience, Propel your career growth Internationally.", "6.8/10"),
                    new Event(Long.parseLong("14"), "Art and Craft Fair", "21-04-2023", "07:30 PM", "JLN Stadium", "Disney", "50", 420, "event", "Arts and crafts show is a place where works are displayed and sold to a broader sect of buyers. Arts and crafts show is a perfect medium of marketing where the artisans can have a direct contact with larger group of people. The basic nature of such shows varies depending upon the region, climatic condition of the region and the quality of crowd gathered in a fair. ", "7.7/10")
            ).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        eventRepository.saveAll(events);
    }

    public static void main(String[] args) {
        SpringApplication.run(RecommendationServiceApplication.class, args);
    }
}
