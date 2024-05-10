/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ["./src/**/*.{html,js}","./node_modules/flowbite/**/*.js"],
  theme: {
    fontFamily: {
      'poppins': ['Poppins'],
      'tenor': ['Tenor Sans']
    },
    extend: {
      colors: {
        'text-landing': '#08376B'
      },
    },
  },
  
  plugins: [
    require('flowbite/plugin')
  ],
}

