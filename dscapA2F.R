#dataset link: https://www.kaggle.com/datasets/adilshamim8/smart-home-energy-consumption
#Null Hypothesis (H₀): There is no significant difference in average power consumption across room locations, kitchen and bedroom.
#Alternative Hypothesis (H₁): There is a significant difference in average power consumption across room locations, kitchen and bedroom.

group_1 <- df[df$Room.Location == "Kitchen", "Power.Consumption..W."]
group_2 <- df[df$Room.Location == "Bedroom", "Power.Consumption..W."]

mean_1 <- mean(group_1)
mean_2 <- mean(group_2)

sd_1 <- sd(group_1)
sd_2 <- sd(group_2)

n_1 <- length(group_1)
n_2 <- length(group_2)
z_score <- (mean_1 - mean_2) / sqrt((sd_1^2 / n_1) + (sd_2^2 / n_2))
p_value <- 2 * (1 - pnorm(abs(z_score))) 
alpha <- 0.05
print(p_value)
if (p_value < alpha) {
  print("Reject the null hypothesis")
} else {
  print("Fail to reject the null hypothesis")
}

#visualizations

library(ggplot2)

# boxplot
ggplot(df[df$Room.Location %in% c("Kitchen", "Bedroom"), ], aes(x = Room.Location, y = `Power.Consumption..W.`)) +
  geom_boxplot() +
  labs(title = "Power Consumption Distribution by Room Location",
       x = "Room Location", y = "Power Cost") +
  theme_minimal()

# histogram with better readability
ggplot(df[df$Room.Location %in% c("Kitchen", "Bedroom"), ], aes(x = `Power.Consumption..W.`, fill = Room.Location)) +
  geom_histogram(binwidth = 70, position = "dodge", alpha = 0.7) +  # Increase binwidth to group values more
  labs(title = "Power Consumption Distribution by Room Location",
       x = "Power Consumption (W)", y = "Frequency",
       fill = "Room Location") +
  theme_minimal() +
  scale_fill_manual(values = c("Kitchen" = "blue", "Bedroom" = "orange"))


# line graph prep
df$Hour <- as.integer(format(as.POSIXct(df$Timestamp), "%H"))
# Filter the data to include only Office and Bedroom
filtered_data <- df %>% 
  filter(Room.Location %in% c("Kitchen", "Bedroom"))

hourly_consumption <- filtered_data %>%
  group_by(Hour, Room.Location) %>%
  summarise(mean_power_consumption = mean(`Power.Consumption..W.`))

# line graph
ggplot(hourly_consumption, aes(x = Hour, y = mean_power_consumption, color = Room.Location, group = Room.Location)) +
  geom_line() +
  labs(title = "Power Consumption by Hour of Day and Room Location",
       x = "Hour of Day", y = "Mean Power Consumption (W)",
       color = "Room Location") +
  theme_minimal() +
  scale_x_continuous(breaks = 0:23) +  # Show all 24 hours
  scale_color_manual(values = c("Kitchen" = "blue", "Bedroom" = "orange")) 

