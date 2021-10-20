package frontend.parser;

import backend.people.Address;
import backend.people.Person;
import frontend.parser.classes.Day;
import frontend.parser.classes.Request;
import frontend.parser.classes.RequestType;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

/**
 * This class is responsible for reading in the input files and turning that
 * text into meaningful information.
 * You do not need to touch this file.
 */
public class Parser {

    private static Request stringToRequest(String strRequest) {
        String[] sr = strRequest.split(",");
        Address address = new Address(
                Integer.parseInt(sr[6]),
                sr[7],
                Integer.parseInt(sr[8]));
        Person person = new Person(
                sr[0], sr[1],
                Integer.parseInt(sr[2]), address,
                Integer.parseInt(sr[3]), Double.parseDouble(sr[4]),
                Double.parseDouble(sr[5]));
        Request request = new Request(
                person,
                RequestType.fromString(sr[9]),
                Double.parseDouble(sr[10]));
        return request;
    }

    public static Day parse(int dayNum, Path filepath) {
        List<String> lines;
        List<Request> requests = new LinkedList<>();
        try {
            lines = Files.readAllLines(
                    filepath,
                    StandardCharsets.UTF_8);
            for (var line : lines) {
                requests.add(stringToRequest(line));
            }
        }
        catch (IOException exc) {
            System.out.println("Error while parsing. File = " + filepath);
        }
        return new Day(dayNum, requests);
    }

    public static void main(String[] args) throws Exception {
        Path path = Paths.get("src/frontend/resources/Day1.txt");
        parse(1, path);
    }
}
