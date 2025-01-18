<?php
// add_event.php

// Get form data
$title = $_POST['title'];
$description = $_POST['description'];
$date = $_POST['date'];
$time = $_POST['time'];
$location = $_POST['location'];

// Read the existing events.json file
$eventsFile = file_get_contents('events.json');
$eventsData = json_decode($eventsFile, true);

// Add new event to the array
$newEvent = [
    'title' => $title,
    'description' => $description,
    'date' => $date,
    'time' => $time,
    'location' => $location
];

array_unshift($eventsData, $newEvent);

// Write updated data back to the JSON file
file_put_contents('events.json', json_encode($eventsData, JSON_PRETTY_PRINT));

echo "Event added successfully!";
