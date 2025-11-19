<div align="center">
  <img width="396" height="189" alt="stylr_logo" src="https://github.com/user-attachments/assets/0c5e28e1-d61c-4a70-8a9f-c7ce911fb76e" />
  <br><br>
</div>

# Stylr

A modern, fluent Java library for styling terminal output with ANSI escape codes.

[![Java 21+](https://img.shields.io/badge/Java-21%2B-blue.svg)](https://openjdk.java.net/)
[![License](https://img.shields.io/badge/License-MIT-green.svg)](LICENSE)

## Features

✨ **Fluent API** - Chain methods for intuitive styling  
🎨 **Multiple Color Formats** - Basic ANSI, RGB values, and hex codes  
🔄 **Reusable Styles** - Create once, apply to multiple texts  
⚡ **Zero Dependencies** - Lightweight with no external dependencies  
🛡️ **Type Safe** - Compile-time safety with sealed classes  

## Quick Examples

### Simple Colors
```java
// Basic colors
System.out.println(Stylr.of("Error").color(BasicColor.RED).render());
System.out.println(Stylr.of("Success").color(BasicColor.GREEN).bold().render());

// RGB colors
System.out.println(Stylr.of("Orange").color(255, 165, 0).render());

// Hex colors
System.out.println(Stylr.of("Purple").color("#9400D3").render());
```

### Progress Bar
```java
Style progress = Stylr.style().bgColor(BasicColor.GREEN).build();
Style remaining = Stylr.style().bgColor(BasicColor.BRIGHT_BLACK).build();

int percent = 60;
int filled = percent / 5;
int empty = 20 - filled;

String bar = progress.apply("█".repeat(filled)) + 
             remaining.apply("░".repeat(empty));
System.out.println("Progress: [" + bar + "] " + percent + "%");
```

### Rainbow Text
```java
String[] colors = {"#FF0000", "#FF7F00", "#FFFF00", "#00FF00", "#0000FF", "#4B0082", "#9400D3"};
String text = "Rainbow";

for (int i = 0; i < text.length(); i++) {
    System.out.print(Stylr.of(String.valueOf(text.charAt(i))).color(colors[i]).render());
}
System.out.println();
```

### Log Levels
```java
Style error = Stylr.style().color(BasicColor.BRIGHT_RED).bold().build();
Style warn = Stylr.style().color(BasicColor.YELLOW).build();
Style info = Stylr.style().color(BasicColor.BLUE).build();

System.out.println(error.apply("[ERROR]") + " Connection failed");
System.out.println(warn.apply("[WARN]") + " Deprecated method");
System.out.println(info.apply("[INFO]") + " Server started");
```

### Status Indicators
```java
String online = Stylr.of("● Online").color(BasicColor.GREEN).render();
String offline = Stylr.of("● Offline").color(BasicColor.RED).render();
String pending = Stylr.of("● Pending").color(BasicColor.YELLOW).render();

System.out.println("Database: " + online);
System.out.println("Cache: " + offline);
System.out.println("API: " + pending);
```

## Installation

Currently, you need to build Stylr from source as it's not yet published to Maven Central.

```bash
# Clone the repository
git clone https://github.com/your-username/stylr.git
cd stylr

# Build the project
./gradlew build

# Generate JAR
./gradlew jar
```

The JAR will be available in `build/libs/stylr-1.0.0.jar`.

## API Reference

### Entry Points

Stylr provides two main entry points depending on your use case:

#### `Stylr.of(String text)` - Direct Text Styling
Creates a `TextStyler` instance for immediate text styling. Use this when you want to style a specific text once.

```java
// Simple usage
String styled = Stylr.of("Hello World").color(BasicColor.RED).render();

// Complex styling
String complex = Stylr.of("Important Message")
    .color(255, 165, 0)           // Orange color
    .bgColor(BasicColor.BLACK)    // Black background
    .bold()                       // Bold text
    .underlined()                 // Underlined
    .render();
```

#### `Stylr.style()` - Reusable Style Creation
Creates a `StyleDefinitionBuilder` for building reusable styles. Use this when you want to apply the same styling to multiple texts.

```java
// Create reusable style
Style errorStyle = Stylr.style()
    .color(BasicColor.BRIGHT_RED)
    .bold()
    .build();

// Apply to multiple texts
System.out.println(errorStyle.apply("Error 1: Connection failed"));
System.out.println(errorStyle.apply("Error 2: File not found"));
System.out.println(errorStyle.apply("Error 3: Permission denied"));
```

### Color Methods

Stylr supports three color formats for both foreground and background colors:

#### Basic ANSI Colors
Standard terminal colors that work in all ANSI-compatible terminals:

```java
// Foreground colors
.color(BasicColor.RED)           // Standard red
.color(BasicColor.BRIGHT_RED)    // Bright red
.color(BasicColor.GREEN)         // Standard green
.color(BasicColor.BRIGHT_GREEN)  // Bright green

// Background colors
.bgColor(BasicColor.BLUE)        // Standard blue background
.bgColor(BasicColor.BRIGHT_BLUE) // Bright blue background
.bgColor(BasicColor.YELLOW)      // Yellow background
.bgColor(BasicColor.BLACK)       // Black background
```

**Available Colors:**
- Standard: 
  - `BLACK`
  - `RED`
  - `GREEN`
  - `YELLOW`
  - `BLUE`
  - `MAGENTA`
  - `CYAN`
  - `WHITE`


- Bright: 
  - `BRIGHT_BLACK`
  - `BRIGHT_RED`
  - `BRIGHT_GREEN`
  - `BRIGHT_YELLOW`
  - `BRIGHT_BLUE`
  - `BRIGHT_MAGENTA`
  - `BRIGHT_CYAN`
  - `BRIGHT_WHITE`

#### RGB Colors (0-255)
Full color support with 24-bit RGB values:

```java
// Foreground RGB
.color(255, 0, 0)      // Pure red
.color(255, 165, 0)    // Orange
.color(128, 0, 128)    // Purple
.color(0, 255, 127)    // Spring green

// Background RGB
.bgColor(255, 255, 255)  // White background
.bgColor(0, 0, 0)        // Black background
.bgColor(70, 130, 180)   // Steel blue background

// Combining both
String colorful = Stylr.of("Colorful Text")
    .color(255, 255, 255)    // White text
    .bgColor(220, 20, 60)    // Crimson background
    .render();
```

#### Hex Colors
CSS-style hexadecimal colors with support for both 3-digit and 6-digit formats:

**6-digit format (#RRGGBB)**: Full precision with separate values for red, green, and blue
```java
// 6-digit hex (full precision)
.color("#FF0000")      // Red (255, 0, 0)
.color("#00FF00")      // Green (0, 255, 0)
.color("#0000FF")      // Blue (0, 0, 255)
.color("#FF6B35")      // Orange red (255, 107, 53)
.color("#8A2BE2")      // Blue violet (138, 43, 226)
```

**3-digit format (#RGB)**: Shorthand notation where each digit is automatically doubled
```java
// 3-digit hex (shorthand, automatically expanded)
.color("#F00")         // Red (expands to #FF0000 → 255, 0, 0)
.color("#0F0")         // Green (expands to #00FF00 → 0, 255, 0)
.color("#00F")         // Blue (expands to #0000FF → 0, 0, 255)
.color("#FA0")         // Orange (expands to #FFAA00 → 255, 170, 0)
```

**Background hex colors**: Same formats work for backgrounds
```java
// Background hex
.bgColor("#2F4F4F")    // Dark slate gray
.bgColor("#FFE4E1")    // Misty rose
.bgColor("#F0F")       // Magenta (3-digit format)

// Mixed formats
String rainbow = Stylr.of("Mixed Colors")
    .color("#FF69B4")          // Hot pink (6-digit)
    .bgColor(25, 25, 112)      // Midnight blue (RGB)
    .render();
```

### Text Attributes

Apply various text formatting attributes:

#### Basic Attributes
```java
.bold()          // Bold/thick text weight
.italic()        // Slanted text (if terminal supports it)
.underlined()    // Underlined text
.dim()           // Dim/faint text (reduced intensity)
```

#### Advanced Attributes
```java
.reverse()       // Invert foreground and background colors
.hide()          // Hidden text (invisible but selectable)
.slowBlink()     // Slow blinking text (if terminal supports it)
.rapidBlink()    // Rapid blinking text (rarely supported)
```

#### Attribute Combinations
```java
// Multiple attributes can be combined
String emphasized = Stylr.of("Very Important")
    .color(BasicColor.RED)
    .bold()
    .underlined()
    .render();

// All attributes together
String kitchen_sink = Stylr.of("Everything")
    .color("#FF4500")
    .bgColor(BasicColor.BLACK)
    .bold()
    .italic()
    .underlined()
    .dim()
    .render();
```

### Terminal Methods

#### `.render()` - TextStyler only
Generates the final styled string with ANSI escape codes:

```java
String result = Stylr.of("Text")
    .color(BasicColor.GREEN)
    .bold()
    .render();  // Returns: "\u001b[32;1mText\u001b[0m"

// Special cases
Stylr.of("").color(BasicColor.RED).render();  // Returns: "" (empty)
```

#### `.build()` - StyleDefinitionBuilder only
Creates a reusable `Style` object:

```java
Style myStyle = Stylr.style()
    .color(BasicColor.BLUE)
    .italic()
    .build();  // Returns: Style instance
```

#### `.apply(String)` - Style only
Applies a reusable style to text:

```java
Style headerStyle = Stylr.style().color(BasicColor.CYAN).bold().build();

String title = headerStyle.apply("Chapter 1");
String subtitle = headerStyle.apply("Introduction");

// Special cases
headerStyle.apply("");  // Returns: "" (empty)
headerStyle.apply(null);  // Throws: NullPointerException
```

### Method Chaining

All styling methods return the builder instance, allowing for fluent method chaining:

```java
// Long chain example
String result = Stylr.of("Chained Methods")
    .color(255, 100, 200)           // RGB color
    .bgColor("#2C3E50")             // Hex background
    .bold()                         // Bold attribute
    .italic()                       // Italic attribute
    .underlined()                   // Underline attribute
    .render();                      // Final render

// Builder pattern with reusable styles
Style complexStyle = Stylr.style()
    .color(BasicColor.BRIGHT_WHITE)
    .bgColor(BasicColor.BLUE)
    .bold()
    .italic()
    .underlined()
    .build();
```

### Color Precedence Rules

When multiple color methods are called on the same builder, **the last one wins**:

```java
// Only the final color is applied
String text = Stylr.of("Test")
    .color(BasicColor.RED)        // Overridden
    .color(0, 255, 0)            // Overridden  
    .color("#0080FF")            // Final color (blue)
    .render();

// Same applies to background colors
String bg = Stylr.of("Background")
    .bgColor(BasicColor.RED)      // Overridden
    .bgColor("#00FF00")          // Final background (green)
    .render();

// Foreground and background are independent
String independent = Stylr.of("Independent")
    .color(BasicColor.RED)        // Kept (foreground)
    .bgColor(BasicColor.BLUE)     // Kept (background)
    .color(BasicColor.GREEN)      // Overrides only foreground
    .render();  // Result: green text on blue background
```


## Terminal Compatibility

| Terminal | Basic Colors | RGB Colors | Notes |
|----------|--------------|------------|-------|
| Windows Terminal | ✅ | ✅ | Recommended |
| PowerShell 7+ | ✅ | ✅ | Full support |
| Linux/Unix terminals | ✅ | ✅ | Full support |
| macOS Terminal | ✅ | ✅ | Full support |
| IntelliJ IDEA | ✅ | ✅ | Console output |
| VS Code | ✅ | ✅ | Integrated terminal |
| Old Windows CMD | ❌ | ❌ | No ANSI support |

## Requirements

- **Java 21+** (uses sealed classes and records)
- **ANSI-compatible terminal** (most modern terminals)


## Contributing

We welcome contributions to Stylr! Whether you're fixing bugs, adding features, or improving documentation, your help is appreciated.

### How to Contribute

1. **Fork the repository**
   ```bash
   git clone https://github.com/bitmeshi/stylr.git
   cd stylr
   ```

2. **Create a feature branch**
   ```bash
   git checkout -b feature/amazing-feature
   ```

3. **Make your changes**
   - Write clear, documented code
   - Follow existing code style and conventions
   - Add tests for new functionality
   - Update documentation as needed

4. **Test your changes**
   ```bash
   ./gradlew test
   ./gradlew build
   ```

5. **Commit and push**
   ```bash
   git commit -m "Add amazing feature"
   git push origin feature/amazing-feature
   ```

6. **Submit a pull request**
   - Describe your changes clearly
   - Reference any related issues
   - Ensure all tests pass

### Development Guidelines

- **Code Style**: Follow Java naming conventions and existing patterns
- **Testing**: Write unit tests for new features and bug fixes
- **Documentation**: Update Javadoc and README for API changes
- **Compatibility**: Maintain Java 21+ compatibility

## Issues

Found a bug or have a feature request? We'd love to hear from you!

### Reporting Bugs

When reporting bugs, please include:

- **Java version** and terminal information
- **Minimal code example** that reproduces the issue
- **Expected behavior** vs actual behavior
- **Error messages** or stack traces if applicable

### Feature Requests

For feature requests, please describe:

- **Use case**: What problem does this solve?
- **Proposed API**: How should it work?
- **Examples**: Show how you'd use the feature

### Common Issues

| Issue | Cause | Solution |
|-------|-------|----------|
| Colors not showing | Old terminal | Use Windows Terminal or update terminal |
| RGB colors not working | Limited terminal support | Try basic colors or update terminal |
| Compilation errors | Wrong Java version | Ensure Java 21+ is installed |

## Acknowledgments

Stylr was inspired by terminal styling libraries from other languages:

- **[chalk](https://github.com/chalk/chalk)** (JavaScript/Node.js) - The gold standard for terminal styling
- **[colorama](https://github.com/tartley/colorama)** (Python) - Simple cross-platform colored terminal text
- **[colored](https://github.com/mackwic/colored)** (Rust) - Elegant terminal colors with a fluent API

Special thanks to:
- The **Java community** for modern language features (sealed classes, records)
- **Terminal emulator developers** for cross-platform ANSI escape code support
- **Contributors and early adopters** who helped shape this library

## License

MIT License - see [LICENSE](LICENSE) file for details.

---

Made with ❤️ and Java.
