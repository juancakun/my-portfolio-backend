package portfolio.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import portfolio.model.PersonalInfo;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PersonalInfoRepositoryImpl implements IPersonalInfoRepository {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<PersonalInfo> personalInfoRowMapper = (rs, numRow) -> {
        PersonalInfo info = new PersonalInfo();

        info.setId(rs.getLong("id"));
        info.setFirstName(rs.getString("firstName"));
        info.setLastName(rs.getString("lastName"));
        info.setTitle(rs.getString("title"));
        info.setProfileDescription(rs.getString("profileDescription"));
        info.setProfileImageUrl(rs.getString("profileImageUrl"));
        info.setYearsOfExperience(
                rs.getObject("yearsOfExperience", Integer.class)
        );
        info.setEmail(rs.getString("email"));
        info.setPhone(rs.getString("phone"));
        info.setLinkedinUrl(rs.getString("linkedinUrl"));
        info.setGithubUrl(rs.getString("githubUrl"));

        return info;
    };

    @Override
    public PersonalInfo save(PersonalInfo personalInfo) {

        if (personalInfo.getId() == null) {

            String sql = """
                    INSERT INTO personal_info (
                        first_name,
                        last_name,
                        title,
                        profile_description,
                        profile_image_url,
                        years_of_experience,
                        email,
                        phone,
                        linkedin_url,
                        github_url
                    )
                    VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                    """;

            KeyHolder keyHolder = new GeneratedKeyHolder();

            jdbcTemplate.update(connection -> {

                PreparedStatement ps = connection.prepareStatement(
                        sql,
                        new String[]{"id"}
                );

                ps.setString(1, personalInfo.getFirstName());
                ps.setString(2, personalInfo.getLastName());
                ps.setString(3, personalInfo.getTitle());
                ps.setString(4, personalInfo.getProfileDescription());
                ps.setString(5, personalInfo.getProfileImageUrl());

                if (personalInfo.getYearsOfExperience() != null) {
                    ps.setObject(6, personalInfo.getYearsOfExperience());
                } else {
                    ps.setNull(6, java.sql.Types.INTEGER);
                }

                ps.setString(7, personalInfo.getEmail());
                ps.setString(8, personalInfo.getPhone());
                ps.setString(9, personalInfo.getLinkedinUrl());
                ps.setString(10, personalInfo.getGithubUrl());

                return ps;

            }, keyHolder);

            personalInfo.setId(
                    Objects.requireNonNull(keyHolder.getKey()).longValue()
            );

        } else {

            String sql = """
                    UPDATE personal_info
                    SET first_name = ?,
                        last_name = ?,
                        title = ?,
                        profile_description = ?,
                        profile_image_url = ?,
                        years_of_experience = ?,
                        email = ?,
                        phone = ?,
                        linkedin_url = ?,
                        github_url = ?
                    WHERE id = ?
                    """;

            jdbcTemplate.update(
                    sql,
                    personalInfo.getFirstName(),
                    personalInfo.getLastName(),
                    personalInfo.getTitle(),
                    personalInfo.getProfileDescription(),
                    personalInfo.getProfileImageUrl(),
                    personalInfo.getYearsOfExperience(),
                    personalInfo.getEmail(),
                    personalInfo.getPhone(),
                    personalInfo.getLinkedinUrl(),
                    personalInfo.getGithubUrl(),
                    personalInfo.getId()
            );
        }

        return personalInfo;
    }

    @Override
    public Optional<PersonalInfo> findById(Long id) {

        String sql = """
                SELECT
                    id,
                    first_name AS firstName,
                    last_name AS lastName,
                    title,
                    profile_description AS profileDescription,
                    profile_image_url AS profileImageUrl,
                    years_of_experience AS yearsOfExperience,
                    email,
                    phone,
                    linkedin_url AS linkedinUrl,
                    github_url AS githubUrl
                FROM personal_info
                WHERE id = ?
                """;

        try {
            return Optional.ofNullable(
                    jdbcTemplate.queryForObject(
                            sql,
                            personalInfoRowMapper,
                            id
                    )
            );

        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<PersonalInfo> findAll() {

        String sql = """
                SELECT
                    id,
                    first_name AS firstName,
                    last_name AS lastName,
                    title,
                    profile_description AS profileDescription,
                    profile_image_url AS profileImageUrl,
                    years_of_experience AS yearsOfExperience,
                    email,
                    phone,
                    linkedin_url AS linkedinUrl,
                    github_url AS githubUrl
                FROM personal_info
                """;

        return jdbcTemplate.query(
                sql,
                personalInfoRowMapper
        );
    }

    @Override
    public void deleteById(Long id) {

        String sql = "DELETE FROM personal_info WHERE id = ?";

        jdbcTemplate.update(sql, id);
    }
}