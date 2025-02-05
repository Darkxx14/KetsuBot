
<div align="center">

![Ketsu Logo](img/ketsu.png "Ketsu")

# Ketsu
**Ketsu** is a feature-rich Discord bot that generates random NSFW hentai GIFs. Built with efficiency and simplicity in mind, Ketsu is perfect for adding a touch of fun to your Discord server.

</div>

---

## Getting Started

### Prerequisites
To run or build Ketsu, ensure you have the following installed:
- **Java 17 or later**
- **Maven** (for building the project)
- A **Discord bot token** (get one from the [Discord Developer Portal](https://discord.com/developers/applications)).

---

### Installation

1. **Clone this repository**:
   ```bash
   git clone https://github.com/Darkxx14/KetsuBot.git
   cd KetsuBot
   ```

2. **Build the project**:
   ```bash
   mvn clean package
   ```

3. **Run the bot**:
   ```bash
   java -jar target/Ketsu-1.0.0.jar
   ```

4. **Configure your bot**:
   Update the `bot.json` file with your Discord bot token.

---

## Commands
Currently Ketsu has only one command:
- `/nsfw <category>` - Generate a random NSFW hentai GIF.

---

## Contributing
Contributions are welcome! If you’d like to contribute, please follow these steps:
1. Fork the repository.
2. Create a new branch for your feature or fix:
   ```bash
   git checkout -b feature-name
   ```
3. Commit your changes:
   ```bash
   git commit -m "hey"
   ```
4. Push your branch and create a pull request.

---

## License
This project is licensed under the **MIT License**. See the [LICENSE](LICENSE) file for details.
---