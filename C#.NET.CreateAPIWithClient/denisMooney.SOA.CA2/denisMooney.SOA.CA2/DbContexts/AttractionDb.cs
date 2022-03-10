using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using denisMooney.SOA.CA2.Entities;
using Microsoft.EntityFrameworkCore;

namespace denisMooney.SOA.CA2.DbContexts
{
    public class AttractionDb : DbContext
    {
        public AttractionDb(DbContextOptions<AttractionDb> options)
           : base(options)
        {
        }
        //DbSet variables used to CREATE, READ, UPDATE, DELETE 
        public DbSet<Facility> Facilites { get; set; }
        public DbSet<Attraction> Attractions { get; set; }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            // seed the database with dummy data
            modelBuilder.Entity<Facility>().HasData(
                new Facility()
                {
                    Id = Guid.Parse("d28888e9-2ba9-473a-a40f-e38cb54f9b35"),
                    Name = "Scenic"
                },
                new Facility()
                {
                    Id = Guid.Parse("da2fd609-d754-4feb-8acd-c4f9ff13ba96"),
                    Name = "Leisure"
                },
                new Facility()
                {
                    Id = Guid.Parse("2902b665-1190-4c70-9915-b9c2d7680450"),
                    Name = "Shopping Centre"
                }
                );

            modelBuilder.Entity<Attraction>().HasData(
               new Attraction
               {
                   Id = Guid.Parse("5b1c2b4d-48c7-402a-80c3-cc796ad49c6b"),
                   FacilityId = Guid.Parse("d28888e9-2ba9-473a-a40f-e38cb54f9b35"),
                   Name = "Cliffs of Moher",
                   Description = "Nice view on the West coast of Ireland."
               },
               new Attraction
               {
                   Id = Guid.Parse("d8663e5e-7494-4f81-8739-6e0de1bea7ee"),
                   FacilityId = Guid.Parse("2902b665-1190-4c70-9915-b9c2d7680450"),
                   Name = "The Marshes Shopping Centre",
                   Description = "Class place to shop!"
               }
               );

            base.OnModelCreating(modelBuilder);
        }

    }
}
