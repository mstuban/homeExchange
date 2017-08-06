package hr.mstuban.homeexchange.controller.facade;


import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import hr.mstuban.homeexchange.domain.Home;
import hr.mstuban.homeexchange.domain.QHome;
import hr.mstuban.homeexchange.domain.form.GenerateStatisticsForm;
import hr.mstuban.homeexchange.services.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.codehaus.plexus.util.StringUtils.isBlank;

/**
 * Created by mstuban on 06/08/17.
 */
@Component
public class StatisticsFacade {

    @Autowired
    private HomeService homeService;

    public Iterable<Home> searchHomeByFilter(GenerateStatisticsForm generateStatisticsForm) {

        Predicate predicate = PredicateBuilder.builder()
                .city(generateStatisticsForm.getCity())
                .country(generateStatisticsForm.getCountry())
                .type(generateStatisticsForm.getType())
                .available(generateStatisticsForm.isAvailable())
                .timeOfExchangeInMonthsFrom(generateStatisticsForm.getTimeOfExchangeInMonthsMin())
                .timeOfExchangeInMonthsTo(generateStatisticsForm.getTimeOfExchangeInMonthsMax())
                .cleanlinessRatingFrom(generateStatisticsForm.getCleanlinessMin())
                .cleanlinessRatingTo(generateStatisticsForm.getCleanlinessMax())
                .comfortRatingFrom(generateStatisticsForm.getComfortMin())
                .comfortRatingTo(generateStatisticsForm.getComfortMax())
                .hospitalityRatingFrom(generateStatisticsForm.getHospitalityMin())
                .hospitalityRatingTo(generateStatisticsForm.getHospitalityMax())
                .valueForMoneyRatingFrom(generateStatisticsForm.getValueForMoneyMin())
                .valueForMoneyRatingTo(generateStatisticsForm.getValueForMoneyMax())
                .facilitiesRatingFrom(generateStatisticsForm.getFacilitiesMin())
                .facilitiesRatingTo(generateStatisticsForm.getFacilitiesMax())
                .sizeInSquareMetersFrom(generateStatisticsForm.getSizeInSquareMetersMin())
                .sizeInSquareMetersTo(generateStatisticsForm.getSizeInSquareMetersMax())
                .build();

        return homeService.findAll(predicate);
    }

    static final class PredicateBuilder {

        private QHome qHome = QHome.home;

        private BooleanBuilder booleanBuilder = new BooleanBuilder();

        private PredicateBuilder() {
        }

        public static PredicateBuilder builder() {
            return new PredicateBuilder();
        }

        public Predicate build() {
            return booleanBuilder;
        }

        public PredicateBuilder country(String country) {
            if (country != null && !isBlank(country)) {
                booleanBuilder.and(qHome.address.country.containsIgnoreCase(country));
            }
            return this;
        }

        public PredicateBuilder city(String city) {
            if (city != null && !isBlank(city)) {
                booleanBuilder.and(qHome.address.city.containsIgnoreCase(city));
            }

            return this;
        }

        public PredicateBuilder type(String type) {
            if (type != null && !isBlank(type)) {
                booleanBuilder.and(qHome.type.containsIgnoreCase(type));
            }

            return this;
        }

        public PredicateBuilder available(boolean available) {
            booleanBuilder.and(qHome.available.eq(available));

            return this;
        }

        public PredicateBuilder timeOfExchangeInMonthsFrom(Integer fromValue) {

            if (fromValue != null) {
                booleanBuilder.and(qHome.timeOfExchangeInMonths.goe(fromValue));
            }

            return this;
        }

        public PredicateBuilder timeOfExchangeInMonthsTo(Integer toValue) {

            if (toValue != null) {
                booleanBuilder.and(qHome.timeOfExchangeInMonths.loe(toValue));
            }

            return this;
        }

        public PredicateBuilder sizeInSquareMetersFrom(Integer fromValue) {

            if (fromValue != null) {
                booleanBuilder.and(qHome.sizeInSquareMeters.goe(fromValue));
            }

            return this;
        }

        public PredicateBuilder sizeInSquareMetersTo(Integer toValue) {

            if (toValue != null) {
                booleanBuilder.and(qHome.sizeInSquareMeters.loe(toValue));
            }

            return this;
        }

        public PredicateBuilder hospitalityRatingFrom(Double fromValue) {

            if (fromValue != null) {

                booleanBuilder.and(qHome.averageHomeRatings.get("averageHospitalityRating").goe(fromValue));
            }
            return this;
        }

        public PredicateBuilder hospitalityRatingTo(Double toValue) {
            if (toValue != null) {


                booleanBuilder.and(qHome.averageHomeRatings.get("averageHospitalityRating").loe(toValue));

            }
            return this;
        }

        public PredicateBuilder comfortRatingFrom(Double fromValue) {
            if (fromValue != null) {


                booleanBuilder.and(qHome.averageHomeRatings.get("averageComfortRating").goe(fromValue));
            }

            return this;
        }

        public PredicateBuilder comfortRatingTo(Double toValue) {
            if (toValue != null) {

                booleanBuilder.and(qHome.averageHomeRatings.get("averageComfortRating").loe(toValue));

            }
            return this;
        }

        public PredicateBuilder facilitiesRatingFrom(Double fromValue) {
            if (fromValue != null) {

                booleanBuilder.and(qHome.averageHomeRatings.get("averageFacilitiesRating").goe(fromValue));
            }

            return this;
        }

        public PredicateBuilder facilitiesRatingTo(Double toValue) {
            if (toValue != null) {

                booleanBuilder.and(qHome.averageHomeRatings.get("averageFacilitiesRating").loe(toValue));

            }
            return this;
        }

        public PredicateBuilder cleanlinessRatingFrom(Double fromValue) {
            if (fromValue != null) {

                booleanBuilder.and(qHome.averageHomeRatings.get("averageCleanlinessRating").goe(fromValue));
            }

            return this;
        }

        public PredicateBuilder cleanlinessRatingTo(Double toValue) {
            if (toValue != null) {

                booleanBuilder.and(qHome.averageHomeRatings.get("averageCleanlinessRating").loe(toValue));
            }

            return this;
        }

        public PredicateBuilder valueForMoneyRatingFrom(Double fromValue) {
            if (fromValue != null) {

                booleanBuilder.and(qHome.averageHomeRatings.get("averageValueForMoneyRating").goe(fromValue));

            }
            return this;
        }

        public PredicateBuilder valueForMoneyRatingTo(Double toValue) {

            if (toValue != null) {

                booleanBuilder.and(qHome.averageHomeRatings.get("averageValueForMoneyRating").loe(toValue));
            }
            return this;
        }
    }

}
