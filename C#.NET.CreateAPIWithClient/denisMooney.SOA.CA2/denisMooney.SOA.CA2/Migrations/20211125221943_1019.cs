using System;
using Microsoft.EntityFrameworkCore.Migrations;

namespace denisMooney.SOA.CA2.Migrations
{
    public partial class _1019 : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.CreateTable(
                name: "Facilites",
                columns: table => new
                {
                    Id = table.Column<Guid>(type: "uniqueidentifier", nullable: false),
                    Name = table.Column<string>(type: "nvarchar(100)", maxLength: 100, nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Facilites", x => x.Id);
                });

            migrationBuilder.CreateTable(
                name: "Attractions",
                columns: table => new
                {
                    Id = table.Column<Guid>(type: "uniqueidentifier", nullable: false),
                    Name = table.Column<string>(type: "nvarchar(100)", maxLength: 100, nullable: false),
                    Description = table.Column<string>(type: "nvarchar(1500)", maxLength: 1500, nullable: false),
                    FacilityId = table.Column<Guid>(type: "uniqueidentifier", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Attractions", x => x.Id);
                    table.ForeignKey(
                        name: "FK_Attractions_Facilites_FacilityId",
                        column: x => x.FacilityId,
                        principalTable: "Facilites",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.InsertData(
                table: "Facilites",
                columns: new[] { "Id", "Name" },
                values: new object[] { new Guid("d28888e9-2ba9-473a-a40f-e38cb54f9b35"), "Scenic" });

            migrationBuilder.InsertData(
                table: "Facilites",
                columns: new[] { "Id", "Name" },
                values: new object[] { new Guid("da2fd609-d754-4feb-8acd-c4f9ff13ba96"), "Leisure" });

            migrationBuilder.InsertData(
                table: "Facilites",
                columns: new[] { "Id", "Name" },
                values: new object[] { new Guid("2902b665-1190-4c70-9915-b9c2d7680450"), "Shopping Centre" });

            migrationBuilder.InsertData(
                table: "Attractions",
                columns: new[] { "Id", "Description", "FacilityId", "Name" },
                values: new object[] { new Guid("5b1c2b4d-48c7-402a-80c3-cc796ad49c6b"), "Nice view on the West coast of Ireland.", new Guid("d28888e9-2ba9-473a-a40f-e38cb54f9b35"), "Cliffs of Moher" });

            migrationBuilder.InsertData(
                table: "Attractions",
                columns: new[] { "Id", "Description", "FacilityId", "Name" },
                values: new object[] { new Guid("d8663e5e-7494-4f81-8739-6e0de1bea7ee"), "Class place to shop!", new Guid("2902b665-1190-4c70-9915-b9c2d7680450"), "The Marshes Shopping Centre" });

            migrationBuilder.CreateIndex(
                name: "IX_Attractions_FacilityId",
                table: "Attractions",
                column: "FacilityId");
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "Attractions");

            migrationBuilder.DropTable(
                name: "Facilites");
        }
    }
}
