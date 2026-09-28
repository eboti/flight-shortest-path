DELETE FROM flight_route
WHERE '${sampleDataEnabled}' = 'false';

DELETE FROM airline
WHERE '${sampleDataEnabled}' = 'false';

DELETE FROM city
WHERE '${sampleDataEnabled}' = 'false';
