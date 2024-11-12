# Use the official PostgreSQL image as a parent image
FROM postgres:14

# Set environment variables
ENV POSTGRES_DB=winelottery
ENV POSTGRES_USER=wineuser
ENV POSTGRES_PASSWORD=multipass123

# Expose the PostgreSQL port
EXPOSE 5432

# Add a health check
HEALTHCHECK --interval=30s --timeout=30s --start-period=5s --retries=3 \
  CMD pg_isready -U ${POSTGRES_USER} -d ${POSTGRES_DB} || exit 1