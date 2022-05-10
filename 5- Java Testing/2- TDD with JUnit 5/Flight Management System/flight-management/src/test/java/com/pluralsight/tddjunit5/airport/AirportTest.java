package com.pluralsight.tddjunit5.airport;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class AirportTest {

    @Nested
    @DisplayName("Given there is an economy flight")
    class EconomyFlightTest {
        private Flight economyFlight;

        @BeforeEach
        void setup() {
            economyFlight = new EconomyFlight("1");
        }

        @Nested
        @DisplayName("When we have usual passenger")
        class UsualPassenger {
            private Passenger mike;

            @BeforeEach
            void setup() {
                mike = new Passenger("Mike", false);
            }
            @Test
            @DisplayName("Then you can add or remove him")
            void testEconomyFlightUsualPassenger() {
                assertAll(
                        () -> assertEquals("1", economyFlight.getId()),
                        () -> assertTrue(economyFlight.addPassenger(mike)),
                        () -> assertEquals(1, economyFlight.getPassengersList().size()),
                        () -> assertTrue(economyFlight.getPassengersList().contains(mike)),
                        () -> assertTrue(economyFlight.removePassenger(mike)),
                        () -> assertEquals(0, economyFlight.getPassengersList().size())
                );
            }

            @DisplayName("Then you can't add him to flight more than once")
            @RepeatedTest(5)
            void testEconomyFlightUsualPassengerAddedOnlyOnce(RepetitionInfo repetitionInfo) {
                for (int i = 0; i < repetitionInfo.getCurrentRepetition(); i++) {
                    economyFlight.addPassenger(mike);
                }
                assertAll(
                        () -> assertEquals(1, economyFlight.getPassengersList().size()),
                        () -> assertTrue(economyFlight.getPassengersList().contains(mike))
                );
            }
        }

        @Nested
        @DisplayName("When we have VIP passenger")
        class VipPassenger {
            @Test
            @DisplayName("Then you can add him but can't remove him")
            void testEconomyFlightVipPassenger() {
                Passenger mike = new Passenger("Mike", true);

                assertAll(
                        () -> assertEquals("1", economyFlight.getId()),
                        () -> assertTrue(economyFlight.addPassenger(mike)),
                        () -> assertEquals(1, economyFlight.getPassengersList().size()),
                        () -> assertTrue(economyFlight.getPassengersList().contains(mike)),
                        () -> assertFalse(economyFlight.removePassenger(mike)),
                        () -> assertEquals(1, economyFlight.getPassengersList().size())
                );
            }
        }
    }

    @Nested
    @DisplayName("Given there is a business flight")
    class BusinessFlightTest {
        private Flight businessFlight;

        @BeforeEach
        void setup() {
            businessFlight = new BusinessFlight("1");
        }

        @Nested
        @DisplayName("When we have usual passenger")
        class usualPassenger {
            @Test
            @DisplayName("Then you can't add him or remove him")
            void testBusinessFlightUsualPassenger() {
                Passenger mike = new Passenger("Mike", false);

                assertAll(
                        () -> assertEquals("1", businessFlight.getId()),
                        () -> assertFalse(businessFlight.addPassenger(mike)),
                        () -> assertEquals(0, businessFlight.getPassengersList().size()),
                        () -> assertFalse(businessFlight.removePassenger(mike)),
                        () -> assertEquals(0, businessFlight.getPassengersList().size())
                );
            }
        }

        @Nested
        @DisplayName("When we have VIP passenger")
        class VipPassenger {
            @Test
            @DisplayName("Then you can add him but can't remove him")
            void testBusinessFlightVipPassenger() {
                Passenger mike = new Passenger("Mike", true);

                assertAll(
                        () -> assertEquals("1", businessFlight.getId()),
                        () -> assertTrue(businessFlight.addPassenger(mike)),
                        () -> assertEquals(1, businessFlight.getPassengersList().size()),
                        () -> assertTrue(businessFlight.getPassengersList().contains(mike)),
                        () -> assertFalse(businessFlight.removePassenger(mike)),
                        () -> assertEquals(1, businessFlight.getPassengersList().size())
                );
            }
        }
    }

    @Nested
    @DisplayName("Given there is a premium flight")
    class PremiumFlightTest {
        private Flight premiumFlight;

        @BeforeEach
        void setup() {
            premiumFlight = new PremiumFlight("1");
        }

        @Nested
        @DisplayName("When we have usual passenger")
        class UsualPassenger {
            @Test
            @DisplayName("Then you can't add or remove him")
            void testPremiumFlightUsualPassenger() {
                Passenger mike = new Passenger("Mike", false);

                assertAll(
                        () -> assertEquals("1", premiumFlight.getId()),
                        () -> assertFalse(premiumFlight.addPassenger(mike)),
                        () -> assertEquals(0, premiumFlight.getPassengersList().size()),
                        () -> assertFalse(premiumFlight.removePassenger(mike)),
                        () -> assertEquals(0, premiumFlight.getPassengersList().size())
                );
            }
        }

        @Nested
        @DisplayName("When we have VIP passenger")
        class VipPassenger {
            @Test
            @DisplayName("Then you can add or remove him")
            void testPremiumFlightVipPassenger() {
                Passenger mike = new Passenger("Mike", true);

                assertAll(
                        () -> assertEquals("1", premiumFlight.getId()),
                        () -> assertTrue(premiumFlight.addPassenger(mike)),
                        () -> assertEquals(1, premiumFlight.getPassengersList().size()),
                        () -> assertTrue(premiumFlight.getPassengersList().contains(mike)),
                        () -> assertTrue(premiumFlight.removePassenger(mike)),
                        () -> assertEquals(0, premiumFlight.getPassengersList().size())
                );
            }
        }
    }
}