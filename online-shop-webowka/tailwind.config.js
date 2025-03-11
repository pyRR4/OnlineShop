import daisyui from "daisyui";

/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ["./src/main/resources/templates/**/*.html"],
  theme: {
    extend: {
      keyframes: {
        fadeOut: {
          '0%, 80%': { opacity: '1', transform: 'translateY(0)' },
          '100%': { opacity: '0', transform: 'translateY(20px)' },
        },
      },
      animation: {
        'fade-out': 'fadeOut 3s ease-in-out forwards',
      },
    },
    fontFamily: {
      poppins: ["Poppins", "sans-serif"],
      lato: ["Lato", "sans-serif"],
      shantell: ["Shantell Sans", "sans-serif"],
      ubuntu: ["Ubutnu", "sans-serif"],
    },
  },
  plugins: [daisyui],
  daisyui: {
    themes: ["cupcake", "sunset"],
  },
  darkMode: ["class", '[data-theme="sunset"]'],
};
