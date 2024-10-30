import java.util.Scanner;

public class WeatherService {

    public static void main(String[] args) {

        String key = "1f50f137-87fa-40a3-af52-f9e01bd6d040";
        String URL = "https://api.weather.yandex.ru/v2/forecast";


        if (args.length >= 3) {
            try {
                double lat = Double.parseDouble(args[0]);
                double lon = Double.parseDouble(args[1]);
                int limit = Integer.parseInt(args[2]);

                YandexWeatherService weatherService = new YandexWeatherService(key, URL);

                String weatherResponse = weatherService.getWeatherByCoordinates(lat, lon);
                System.out.println("JSON" + weatherResponse);


                int temp = weatherService.extractTemperature(weatherResponse);
                System.out.println("now: "+ temp);

                double averageTemp = weatherService.getAverageTemperature(lat, lon, limit);
                System.out.println("averge " + averageTemp);

            } catch (Exception e) {
                System.out.println("Err: " + e.getMessage());
            }
        } else {
            Scanner scanner = new Scanner(System.in);

            try {
                System.out.print("lat:");
                double lat = Double.parseDouble(scanner.nextLine());

                System.out.print("lon:");
                double lon = Double.parseDouble(scanner.nextLine());

                System.out.print("limit: ");
                int limit = Integer.parseInt(scanner.nextLine());

                YandexWeatherService weatherService = new YandexWeatherService(key, URL);

                String weatherResponse = weatherService.getWeatherByCoordinates(lat, lon);
                System.out.println("full: " + weatherResponse);

                int temp = weatherService.extractTemperature(weatherResponse);
                System.out.println("Cейчас: " + temp);

                double averageTemp = weatherService.getAverageTemperature(lat, lon, limit);
                System.out.println("averge" + averageTemp);

            } catch (Exception e) {
                System.out.println("Ошибка: " + e.getMessage());
            }

            scanner.close();
        }
    }
}