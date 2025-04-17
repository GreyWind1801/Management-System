import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react-swc'
import tailwindcss from '@tailwindcss/vite'

// Strictly development CSP headers to allow Vite features
const devCspHeader = `
  default-src 'self';
  script-src 'self' 'unsafe-inline' 'unsafe-eval';
  style-src 'self' 'unsafe-inline';
  img-src 'self' data:;
  font-src 'self' https://fonts.gstatic.com;
  connect-src *;
  object-src 'none';
  base-uri 'self';
  form-action 'self';
  frame-ancestors 'none';
`.replace(/\s{2,}/g, ' ').trim()

export default defineConfig({
  plugins: [react(), tailwindcss()],
  server: {
    headers: {
      'Content-Security-Policy': devCspHeader,
    },
  },
  preview: {
    headers: {
      'Content-Security-Policy': devCspHeader,
    },
  },
})
