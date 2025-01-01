/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./src/main/resources/templates/**/*.html", // Archivos Thymeleaf
    "./src/main/resources/static/**/*.js"       // JS estáticos (opcional)
  ],
  theme: {
    extend: {},
  },
  plugins: [],
};

