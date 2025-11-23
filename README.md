# Spell Check API

This project provides [Zemberek NLP](https://github.com/ahmetaa/zemberek-nlp) as a Spring Boot based REST API for Turkish natural language processing. With this API you can perform spell checking, sentence analysis, morphological analysis and normalization.

---

## 🚀 Getting Started

### Run the Application
### Local
```bash
mvn clean package
java -jar target/spell-check-api.jar
# http://localhost:6001
```

### Docker
```bash
docker build -t spell-check-api .
docker run --rm -d -p 6001:6001 --name spell-check-api spell-check-api
# http://localhost:6001
```

By default, the API runs on port **6001**. To change the port, add the following line to `application.properties`:

```properties
server.port=9090
```

---

## 📌 Endpoints

### 1. Spell Check

#### Single Word Check
- **URL:** `POST /spellcheck`
- **Body:**
```json
{
    "word": "kitab"
}
```

- **Response:**
```json
{
	"input": "kitab",
	"correct": false,
	"suggestions": [
		"Kitap",
		"kitap",
		"kitabı",
		"kitaba",
		"kitabe",
		"Kita'n",
		"Kita'm",
		"kitabi",
		"Kitai",
		"Kita",
		"Kitab'a",
		"Kitab'ı",
		"Kitab"
	]
}
```

---

#### Sentence Check
- **URL:** `POST /spellcheck`
- **Body:**
```json
{
    "text": "Bugün havva çok güzell."
}
```

- **Response:**
```json
[
	{
		"correct": true,
		"suggestions": [],
		"word": "Bugün"
	},
	{
		"correct": false,
		"suggestions": [
			"hava",
			"Havva",
			"Havza",
			"havza",
			"Havsa",
			"havsa",
			"Avva",
			"havra",
			"havda",
			"havla",
			"Havva'm",
			"Havva'n",
			"havya",
			"havca"
		],
		"word": "havva"
	},
	{
		"correct": true,
		"suggestions": [],
		"word": "çok"
	},
	{
		"correct": false,
		"suggestions": [
			"güzel",
			"güzeli",
			"güzelle",
			"güzele",
			"güzelli"
		],
		"word": "güzell"
	},
	{
		"correct": true,
		"suggestions": [],
		"word": "."
	}
]
```

---

### 2. Normalization

- **URL:** `POST /normalize`
- **Body:**
```json
{
	"text": "Yrn okua gidicem"
}
```

- **Response:**
```json
{
	"normalized": "yarın okula gideceğim"
}
```

### 3. Tokenization

- **URL:** `POST /tokenize`
- **Body:**
```json
{
    "text": "Merhaba dünya"
}
```

- **Response:**
```json
{
	"tokens": [
		{
			"surface": "Merhaba",
			"type": "Word",
			"start": 0,
			"end": 6
		},
		{
			"surface": " ",
			"type": "SpaceTab",
			"start": 7,
			"end": 7
		},
		{
			"surface": "dünya",
			"type": "Word",
			"start": 8,
			"end": 12
		},
		{
			"surface": "!",
			"type": "Punctuation",
			"start": 13,
			"end": 13
		}
	]
}
```

---

### 4. Morphological Analyze

- **URL:** `POST /morphology/analyze`
- **Body:**
```json
{
    "text": "kitaplarımızdan"
}
```

- **Response:**
```json
{
	"input": "kitaplarımızdan",
	"analyses": [
		{
			"lemma": "kitap",
			"formatted": "[Kitap:Noun,Prop] kitap:Noun+lar:A3pl+ımız:P1pl+dan:Abl",
			"morphemes": [
				"Noun",
				"A3pl",
				"P1pl",
				"Abl"
			]
		},
		{
			"lemma": "kitap",
			"formatted": "[kitap:Noun] kitap:Noun+lar:A3pl+ımız:P1pl+dan:Abl",
			"morphemes": [
				"Noun",
				"A3pl",
				"P1pl",
				"Abl"
			]
		}
	]
}
```

---

### 5. Sentence Split

- **URL:** `POST /sentence/split`
- **Body:**
```json
{
	"text": "Bugün hava güzel. Dışarı çıkalım."
}
```

- **Response:**
```json
{
	"sentences": [
		"Bugün hava güzel.",
		"Dışarı çıkalım."
	]
}
```

---

### 6. Language Detection

- **URL:** `POST /languages/detect`
- **Body:**
```json
{
    "text": "Merhaba dünya"
}
```

- **Response:**
```json
{
    "language": "tr",
	"confidence": 0.8518513939877888
}
```

---

## 🛠️ Example Usage (cURL)

```bash
curl -X POST http://localhost:6001/spellcheck \
     -H "Content-Type: application/json" \
     -d '{"word":"kitab"}'
```

```bash
curl -X POST http://localhost:6001/spellcheck \
     -H "Content-Type: application/json" \
     -d '{"text":"Bugün havva çok güzell."}'
```

```bash
curl -X POST http://localhost:6001/normalize \
     -H "Content-Type: application/json" \
     -d '{"text":"Bugun hava cok guzel"}'
```

```bash
curl -X POST http://localhost:6001/languages/detect \
     -H "Content-Type: application/json" \
     -d '{"text":"Merhaba dünya"}'
```

---

## 📖 Notes
- Use the `word` parameter for single word spell checking.
- Use the `text` parameter for sentence spell checking.
- Do not send both `word` and `text` at the same time.
- Normalizer requires resource files (`normalization/lm`, `normalization/data`) to be copied into the Docker image.
```
